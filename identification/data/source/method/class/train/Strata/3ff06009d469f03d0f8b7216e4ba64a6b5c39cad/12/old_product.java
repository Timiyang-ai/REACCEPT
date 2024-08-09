public PointSensitivityBuilder presentValueSensitivity(
      CmsPeriod cmsPeriod,
      RatesProvider provider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    Currency ccy = cmsPeriod.getCurrency();
    if (provider.getValuationDate().isAfter(cmsPeriod.getPaymentDate())) {
      return PointSensitivityBuilder.none();
    }
    Swap swap = cmsPeriod.getUnderlyingSwap();
    ExpandedSwap expandedSwap = swap.expand();
    double dfPayment = provider.discountFactor(ccy, cmsPeriod.getPaymentDate());
    ZonedDateTime valuationDate = swaptionVolatilities.getValuationDateTime();
    LocalDate fixingDate = cmsPeriod.getFixingDate();
    double expiryTime = swaptionVolatilities.relativeTime(
        fixingDate.atTime(valuationDate.toLocalTime()).atZone(valuationDate.getZone()));
    double tenor = swaptionVolatilities.tenor(swap.getStartDate(), swap.getEndDate());
    double shift = swaptionVolatilities.getParameters().shift(expiryTime, tenor);
    double strike = cmsPeriod.getStrike();
    if (!fixingDate.isAfter(valuationDate.toLocalDate())) {
      OptionalDouble fixedRate = provider.timeSeries(cmsPeriod.getIndex()).get(fixingDate);
      if (fixedRate.isPresent()) {
        double payoff = 0d;
        switch (cmsPeriod.getCmsPeriodType()) {
          case CAPLET:
            payoff = Math.max(fixedRate.getAsDouble() - strike, 0d);
            break;
          case FLOORLET:
            payoff = Math.max(strike - fixedRate.getAsDouble(), 0d);
            break;
          case COUPON:
            payoff = fixedRate.getAsDouble();
            break;
          default:
            throw new IllegalArgumentException("unsupported CMS type");
        }
        return provider.discountFactors(ccy).zeroRatePointSensitivity(
            cmsPeriod.getPaymentDate()).multipliedBy(payoff * cmsPeriod.getNotional() * cmsPeriod.getYearFraction());
      } else if (fixingDate.isBefore(valuationDate.toLocalDate())) {
        throw new IllegalArgumentException(Messages.format(
            "Unable to get fixing for {} on date {}, no time-series supplied", cmsPeriod.getIndex(), fixingDate));
      }
    }
    strike = cmsPeriod.getCmsPeriodType().equals(CmsPeriodType.COUPON) ? 0d : strike + shift;
    double forward = swapPricer.parRate(expandedSwap, provider) + shift;
    double eta = cmsPeriod.getDayCount().relativeYearFraction(cmsPeriod.getPaymentDate(), swap.getStartDate());
    CmsDeltaIntegrantProvider intProv = new CmsDeltaIntegrantProvider(
        cmsPeriod, expandedSwap, swaptionVolatilities, forward, strike, expiryTime, tenor, cutOffStrike + shift, eta);
    double factor = dfPayment / intProv.h(forward) * intProv.g(forward);
    double absoluteTolerance = 1d / (factor * Math.abs(cmsPeriod.getNotional()) * cmsPeriod.getYearFraction());
    RungeKuttaIntegrator1D integrator = new RungeKuttaIntegrator1D(absoluteTolerance, REL_TOL, NUM_ITER);
    double[] bs = intProv.bsbsp(strike);
    double[] n = intProv.getNnp();
    double strikePartPrice = intProv.k(strike) * n[0] * bs[0];
    double integralPartPrice = 0d;
    double integralPart = 0d;
    Function<Double, Double> integrant = intProv.integrant();
    Function<Double, Double> integrantDelta = intProv.integrantDelta();
    try {
      if (intProv.getPutCall().isCall()) {
        integralPartPrice = integrateCall(integrator, integrant, swaptionVolatilities, forward, strike, expiryTime,
            tenor);
        integralPart = dfPayment *
            integrateCall(integrator, integrantDelta, swaptionVolatilities, forward, strike, expiryTime, tenor);
      } else {
        integralPartPrice = integrator.integrate(integrant, 0d, strike);
        integralPart = dfPayment * integrator.integrate(integrantDelta, 0d, strike);
      }
    } catch (Exception e) {
      throw new MathException(e);
    }
    double deltaPD = (strikePartPrice + integralPartPrice) * cmsPeriod.getNotional() * cmsPeriod.getYearFraction();
    double strikePart = dfPayment * intProv.k(strike) * (n[1] * bs[0] + n[0] * bs[1]);
    double deltaFwd = (strikePart + integralPart) * cmsPeriod.getNotional() * cmsPeriod.getYearFraction();
    PointSensitivityBuilder sensiFwd = swapPricer.parRateSensitivity(expandedSwap, provider).multipliedBy(deltaFwd);
    PointSensitivityBuilder sensiDf = provider.discountFactors(ccy)
        .zeroRatePointSensitivity(cmsPeriod.getPaymentDate()).multipliedBy(deltaPD);
    return sensiFwd.combinedWith(sensiDf);
  }