public double presentValueSensitivityStrike(
      CmsPeriod cmsPeriod,
      RatesProvider provider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    ArgChecker.isFalse(cmsPeriod.getCmsPeriodType().equals(CmsPeriodType.COUPON),
        "presentValueSensitivityStrike is not relevant for CMS coupon");
    Currency ccy = cmsPeriod.getCurrency();
    SwapIndex index = cmsPeriod.getIndex();
    if (provider.getValuationDate().isAfter(cmsPeriod.getPaymentDate())) {
      return 0d;
    }
    Swap swap = cmsPeriod.getUnderlyingSwap();
    ExpandedSwap expandedSwap = swap.expand();
    double dfPayment = provider.discountFactor(ccy, cmsPeriod.getPaymentDate());
    ZonedDateTime valuationDate = swaptionVolatilities.getValuationDateTime();
    LocalDate fixingDate = cmsPeriod.getFixingDate();
    double tenor = swaptionVolatilities.tenor(swap.getStartDate(), swap.getEndDate());
    ZonedDateTime expiryDate = fixingDate.atTime(index.getFixingTime()).atZone(index.getFixingZone());
    double expiryTime = swaptionVolatilities.relativeTime(expiryDate);
    double strike =  cmsPeriod.getStrike();
    double shift = swaptionVolatilities.getParameters().shift(expiryTime, tenor);
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
    double forward = swapPricer.parRate(expandedSwap, provider);
    double eta = index.getTemplate().getConvention().getFixedLeg().getDayCount()
        .relativeYearFraction(cmsPeriod.getPaymentDate(), swap.getStartDate());
    CmsIntegrantProvider intProv = new CmsIntegrantProvider(
        cmsPeriod, expandedSwap, swaptionVolatilities, forward, strike, expiryTime, tenor, cutOffStrike, eta);
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
      firstPart = - kpkpp[0] * intProv.bs(strike);
      thirdPart = - integrator.integrate(integrant, -shift, strike);
    }
    double secondPart =
        intProv.k(strike) * intProv.getSabrExtrapolation().priceDerivativeStrike(strike + shift, intProv.getPutCall());
    return cmsPeriod.getNotional() * cmsPeriod.getYearFraction() * factor * (firstPart + secondPart + thirdPart);
  }