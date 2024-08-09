public double parRate(
      ResolvedIborFuture future,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    IborIndexObservation obs = future.getIborRate().getObservation();
    double forward = ratesProvider.iborIndexRates(future.getIndex()).rate(obs);
    LocalDate fixingStartDate = obs.getEffectiveDate();
    LocalDate fixingEndDate = obs.getMaturityDate();
    double fixingYearFraction = obs.getYearFraction();
    double convexity = hwProvider.futuresConvexityFactor(future.getLastTradeDate(), fixingStartDate, fixingEndDate);
    return convexity * forward - (1d - convexity) / fixingYearFraction;
  }