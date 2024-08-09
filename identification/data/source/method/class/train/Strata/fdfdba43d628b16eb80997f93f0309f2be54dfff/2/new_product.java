public SwaptionSabrSensitivity presentValueSensitivitySabrParameter(
      CmsPeriod cmsPeriod,
      RatesProvider provider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    Currency ccy = cmsPeriod.getCurrency();
    SwapIndex index = cmsPeriod.getIndex();
    Swap swap = cmsPeriod.getUnderlyingSwap();
    ExpandedSwap expandedSwap = swap.expand();
    double dfPayment = provider.discountFactor(ccy, cmsPeriod.getPaymentDate());
    ZonedDateTime valuationDate = swaptionVolatilities.getValuationDateTime();
    LocalDate fixingDate = cmsPeriod.getFixingDate();
    ZonedDateTime expiryDate = fixingDate.atTime(index.getFixingTime()).atZone(index.getFixingZone());
    double tenor = swaptionVolatilities.tenor(swap.getStartDate(), swap.getEndDate());
    if (provider.getValuationDate().isAfter(cmsPeriod.getPaymentDate())) {
      return SwaptionSabrSensitivity.of(
          cmsPeriod.getIndex().getTemplate().getConvention(), expiryDate, tenor, ccy, 0d, 0d, 0d, 0d);
    }
    if (!fixingDate.isAfter(valuationDate.toLocalDate())) {
      OptionalDouble fixedRate = provider.timeSeries(cmsPeriod.getIndex()).get(fixingDate);
      if (fixedRate.isPresent()) {
        return SwaptionSabrSensitivity.of(
            cmsPeriod.getIndex().getTemplate().getConvention(), expiryDate, tenor, ccy, 0d, 0d, 0d, 0d);
      } else if (fixingDate.isBefore(valuationDate.toLocalDate())) {
        throw new IllegalArgumentException(Messages.format(
            "Unable to get fixing for {} on date {}, no time-series supplied", cmsPeriod.getIndex(), fixingDate));
      }
    }
    double expiryTime = swaptionVolatilities.relativeTime(expiryDate);
    double shift = swaptionVolatilities.getParameters().shift(expiryTime, tenor);
    double strikeCpn = cmsPeriod.getCmsPeriodType().equals(CmsPeriodType.COUPON) ? -shift : cmsPeriod.getStrike();
    double forward = swapPricer.parRate(expandedSwap, provider);
    double eta = index.getTemplate().getConvention().getFixedLeg().getDayCount()
        .relativeYearFraction(cmsPeriod.getPaymentDate(), swap.getStartDate());
    CmsIntegrantProvider intProv = new CmsIntegrantProvider(
        cmsPeriod, expandedSwap, swaptionVolatilities, forward, strikeCpn, expiryTime, tenor, cutOffStrike, eta);
    double factor = dfPayment / intProv.h(forward) * intProv.g(forward);
    double factor2 = factor * intProv.k(strikeCpn);
    double[] strikePartPrice = intProv.getSabrExtrapolation()
        .priceAdjointSabr(Math.max(0d, strikeCpn + shift), intProv.getPutCall()) // handle tiny but negative number
        .getDerivatives().multipliedBy(factor2).toArray();
    double absoluteTolerance = 1d / (factor * Math.abs(cmsPeriod.getNotional()) * cmsPeriod.getYearFraction());
    RungeKuttaIntegrator1D integrator = new RungeKuttaIntegrator1D(absoluteTolerance, REL_TOL_VEGA, NUM_ITER);
    double[] totalSensi = new double[4];
    for (int loopparameter = 0; loopparameter < 4; loopparameter++) {
      double integralPart = 0d;
      Function<Double, Double> integrant = intProv.integrantVega(loopparameter);
      try {
        if (intProv.getPutCall().isCall()) {
          integralPart = dfPayment *
              integrateCall(integrator, integrant, swaptionVolatilities, forward, strikeCpn, expiryTime, tenor);
        } else {
          integralPart = - dfPayment * integrator.integrate(integrant, -shift, strikeCpn);
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      totalSensi[loopparameter] =
          (strikePartPrice[loopparameter] + integralPart) * cmsPeriod.getNotional() * cmsPeriod.getYearFraction();
    }
    return SwaptionSabrSensitivity.of(cmsPeriod.getIndex().getTemplate().getConvention(),
        expiryDate, tenor, ccy, totalSensi[0], totalSensi[1], totalSensi[2], totalSensi[3]);
  }