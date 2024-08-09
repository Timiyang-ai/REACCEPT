public CurrencyAmount presentValue(
      CmsPeriod cmsPeriod,
      RatesProvider provider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    Currency ccy = cmsPeriod.getCurrency();
    if (provider.getValuationDate().isAfter(cmsPeriod.getPaymentDate())) {
      return CurrencyAmount.zero(ccy);
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
        return CurrencyAmount.of(ccy, dfPayment * payoff * cmsPeriod.getNotional() * cmsPeriod.getYearFraction());
      } else if (fixingDate.isBefore(valuationDate.toLocalDate())) {
        throw new IllegalArgumentException(Messages.format(
            "Unable to get fixing for {} on date {}, no time-series supplied", cmsPeriod.getIndex(), fixingDate));
      }
    }
    double forward = swapPricer.parRate(swap, provider);
    if (expiryTime < MIN_TIME) {
      double payoff = payOff(cmsPeriod.getCmsPeriodType(), strikeCpn, forward);
      return CurrencyAmount.of(ccy, dfPayment * payoff * cmsPeriod.getNotional() * cmsPeriod.getYearFraction());
    }
    double eta = index.getTemplate().getConvention().getFixedLeg().getDayCount()
        .relativeYearFraction(cmsPeriod.getPaymentDate(), swap.getStartDate());
    CmsIntegrantProvider intProv = new CmsIntegrantProvider(
        cmsPeriod, swap, swaptionVolatilities, forward, strikeCpn, expiryTime, tenor, cutOffStrike, eta);
    double factor = dfPayment / intProv.h(forward) * intProv.g(forward);
    double strikePart = factor * intProv.k(strikeCpn) * intProv.bs(strikeCpn);
    double absoluteTolerance = 1d / (factor * Math.abs(cmsPeriod.getNotional()) * cmsPeriod.getYearFraction());
    RungeKuttaIntegrator1D integrator = new RungeKuttaIntegrator1D(absoluteTolerance, REL_TOL, NUM_ITER);
    double integralPart = 0d;
    Function<Double, Double> integrant = intProv.integrant();
    try {
      if (intProv.getPutCall().isCall()) {
        integralPart = dfPayment *
            integrateCall(integrator, integrant, swaptionVolatilities, forward, strikeCpn, expiryTime, tenor);
      } else {
        integralPart = - dfPayment * integrator.integrate(integrant, -shift + ZERO_SHIFT, strikeCpn);
      }
    } catch (Exception e) {
      throw new MathException(e);
    }
    double priceCMS = (strikePart + integralPart);
    if(cmsPeriod.getCmsPeriodType().equals(CmsPeriodType.COUPON)) {
      priceCMS-= dfPayment * shift;
    }
    priceCMS *= cmsPeriod.getNotional() * cmsPeriod.getYearFraction();
    return CurrencyAmount.of(ccy, priceCMS);
  }