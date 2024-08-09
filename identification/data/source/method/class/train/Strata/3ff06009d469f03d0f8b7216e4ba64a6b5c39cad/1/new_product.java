public PointSensitivityBuilder presentValueSensitivity(
      CmsPeriod cmsPeriod,
      RatesProvider provider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    Currency ccy = cmsPeriod.getCurrency();
    if (provider.getValuationDate().isAfter(cmsPeriod.getPaymentDate())) {
      return PointSensitivityBuilder.none();
    }
    SwapIndex index = cmsPeriod.getIndex();
    ResolvedSwap swap = cmsPeriod.getUnderlyingSwap();
    double dfPayment = provider.discountFactor(ccy, cmsPeriod.getPaymentDate());
    ZonedDateTime valuationDate = swaptionVolatilities.getValuationDateTime();
    LocalDate fixingDate = cmsPeriod.getFixingDate();
    double expiryTime = swaptionVolatilities.relativeTime(
        fixingDate.atTime(index.getFixingTime()).atZone(index.getFixingZone()));
    double tenor = swaptionVolatilities.tenor(swap.getStartDate(), swap.getEndDate());
    double shift = swaptionVolatilities.getParameters().shift(expiryTime, tenor);
    double strikeCpn = cmsPeriod.getCmsPeriodType().equals(CmsPeriodType.COUPON) ? -shift : cmsPeriod.getStrike();
    if (!fixingDate.isAfter(valuationDate.toLocalDate())) {
      OptionalDouble fixedRate = provider.timeSeries(cmsPeriod.getIndex()).get(fixingDate);
      if (fixedRate.isPresent()) {
        double payoff = payOff(cmsPeriod.getCmsPeriodType(), strikeCpn, fixedRate.getAsDouble());
        return provider.discountFactors(ccy).zeroRatePointSensitivity(
            cmsPeriod.getPaymentDate()).multipliedBy(payoff * cmsPeriod.getNotional() * cmsPeriod.getYearFraction());
      } else if (fixingDate.isBefore(valuationDate.toLocalDate())) {
        throw new IllegalArgumentException(Messages.format(
            "Unable to get fixing for {} on date {}, no time-series supplied", cmsPeriod.getIndex(), fixingDate));
      }
    }
    double forward = swapPricer.parRate(swap, provider);
    double eta = index.getTemplate().getConvention().getFixedLeg().getDayCount()
        .relativeYearFraction(cmsPeriod.getPaymentDate(), swap.getStartDate());
    CmsDeltaIntegrantProvider intProv = new CmsDeltaIntegrantProvider(
        cmsPeriod, swap, swaptionVolatilities, forward, strikeCpn, expiryTime, tenor, cutOffStrike, eta);
    double factor = dfPayment / intProv.h(forward) * intProv.g(forward);
    double absoluteTolerance = 1d / (factor * Math.abs(cmsPeriod.getNotional()) * cmsPeriod.getYearFraction());
    RungeKuttaIntegrator1D integrator = new RungeKuttaIntegrator1D(absoluteTolerance, REL_TOL, NUM_ITER);
    double[] bs = intProv.bsbsp(strikeCpn);
    double[] n = intProv.getNnp();
    double strikePartPrice = intProv.k(strikeCpn) * n[0] * bs[0];
    double integralPartPrice = 0d;
    double integralPart = 0d;
    Function<Double, Double> integrant = intProv.integrant();
    Function<Double, Double> integrantDelta = intProv.integrantDelta();
    try {
      if (intProv.getPutCall().isCall()) {
        integralPartPrice =
            integrateCall(integrator, integrant, swaptionVolatilities, forward, strikeCpn, expiryTime, tenor);
        integralPart = dfPayment *
            integrateCall(integrator, integrantDelta, swaptionVolatilities, forward, strikeCpn, expiryTime, tenor);
      } else {
        integralPartPrice = - integrator.integrate(integrant, -shift + ZERO_SHIFT, strikeCpn);
        integralPart = - dfPayment * integrator.integrate(integrantDelta, -shift, strikeCpn);
      }
    } catch (Exception e) {
      throw new MathException(e);
    }
    double deltaPD = strikePartPrice + integralPartPrice;
    if (cmsPeriod.getCmsPeriodType().equals(CmsPeriodType.COUPON)) {
      deltaPD -= shift;
    }
    deltaPD *= cmsPeriod.getNotional() * cmsPeriod.getYearFraction();
    double strikePart = dfPayment * intProv.k(strikeCpn) * (n[1] * bs[0] + n[0] * bs[1]);
    double deltaFwd = (strikePart + integralPart) * cmsPeriod.getNotional() * cmsPeriod.getYearFraction();
    PointSensitivityBuilder sensiFwd = swapPricer.parRateSensitivity(swap, provider).multipliedBy(deltaFwd);
    PointSensitivityBuilder sensiDf = provider.discountFactors(ccy)
        .zeroRatePointSensitivity(cmsPeriod.getPaymentDate()).multipliedBy(deltaPD);
    return sensiFwd.combinedWith(sensiDf);
  }