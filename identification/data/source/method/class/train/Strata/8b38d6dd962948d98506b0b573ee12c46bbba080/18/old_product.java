public PointSensitivities priceSensitivity(
      ResolvedIborFuture future,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    LocalDate fixingStartDate = future.getObservation().getEffectiveDate();
    LocalDate fixingEndDate = future.getObservation().getMaturityDate();
    double convexity = hwProvider.futuresConvexityFactor(future.getLastTradeDate(), fixingStartDate, fixingEndDate);
    IborRateSensitivity sensi = IborRateSensitivity.of(future.getObservation(), -convexity);
    // The sensitivity should be to no currency or currency XXX. To avoid useless conversion, the dimension-less 
    // price sensitivity is reported in the future currency.
    return PointSensitivities.of(sensi);
  }