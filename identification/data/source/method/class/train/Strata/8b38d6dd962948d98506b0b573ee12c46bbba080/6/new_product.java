public PointSensitivities priceSensitivityRates(
      ResolvedIborFuture future,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    IborIndexObservation obs = future.getIborRate().getObservation();
    LocalDate fixingStartDate = obs.getEffectiveDate();
    LocalDate fixingEndDate = obs.getMaturityDate();
    double convexity = hwProvider.futuresConvexityFactor(future.getLastTradeDate(), fixingStartDate, fixingEndDate);
    IborRateSensitivity sensi = IborRateSensitivity.of(obs, -convexity);
    // The sensitivity should be to no currency or currency XXX. To avoid useless conversion, the dimension-less 
    // price sensitivity is reported in the future currency.
    return PointSensitivities.of(sensi);
  }