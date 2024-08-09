public double presentValueSensitivityStrike(
      CmsPeriod cmsPeriod,
      RatesProvider provider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    ArgChecker.isFalse(cmsPeriod.getCmsPeriodType().equals(CmsPeriodType.COUPON),
        "presentValueSensitivityStrike is not relevant for CMS coupon");
    Currency ccy = cmsPeriod.getCurrency();
    if (provider.getValuationDate().isAfter(cmsPeriod.getPaymentDate())) {
      return 0d;
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
            payoff = fixedRate.getAsDouble() >= strike ? -1d : 0d;
            break;
          case FLOORLET:
            payoff = fixedRate.getAsDouble() < strike ? 1d : 0d;
            break;
          default:
            throw new IllegalArgumentException("unsupported CMS type");
        }
        return payoff * cmsPeriod.getNotional() * cmsPeriod.getYearFraction() * dfPayment;
      } else if (fixingDate.isBefore(valuationDate.toLocalDate())) {
        throw new IllegalArgumentException(Messages.format(
            "Unable to get fixing for {} on date {}, no time-series supplied", cmsPeriod.getIndex(), fixingDate));
      }
    }
    strike = cmsPeriod.getCmsPeriodType().equals(CmsPeriodType.COUPON) ? 0d : strike + shift;
    double forward = swapPricer.parRate(expandedSwap, provider) + shift;
    double eta = cmsPeriod.getDayCount().relativeYearFraction(cmsPeriod.getPaymentDate(), swap.getStartDate());
    CmsIntegrantProvider intProv = new CmsIntegrantProvider(
        cmsPeriod, expandedSwap, swaptionVolatilities, forward, strike, expiryTime, tenor, cutOffStrike + shift, eta);
    double factor = dfPayment * intProv.g(forward) / intProv.h(forward);
    double absoluteTolerance = 1.0E-9;
    RungeKuttaIntegrator1D integrator = new RungeKuttaIntegrator1D(absoluteTolerance, REL_TOL_STRIKE, NUM_ITER);
    double[] kpkpp = intProv.kpkpp(strike);
    double firstPart;
    double thirdPart;
    Function<Double, Double> integrant = intProv.integrantDualDelta();
    if (intProv.getPutCall().isCall()) {
      firstPart = -kpkpp[0] * intProv.bs(strike);
      thirdPart = integrateCall(integrator, integrant, swaptionVolatilities, forward, strike, expiryTime, tenor);
    } else {
      firstPart = 3d * kpkpp[0] * intProv.bs(strike);
      thirdPart = integrator.integrate(integrant, 0d, strike);
    }
    double secondPart =
        intProv.k(strike) * intProv.getSabrExtrapolation().priceDerivativeStrike(strike, intProv.getPutCall());
    return cmsPeriod.getNotional() * cmsPeriod.getYearFraction() * factor * (firstPart + secondPart + thirdPart);
  }