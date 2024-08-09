public double parRate(
      ResolvedIborFuture future,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    double forward = ratesProvider.iborIndexRates(future.getIndex()).rate(future.getObservation());
    LocalDate fixingStartDate = future.getObservation().getEffectiveDate();
    LocalDate fixingEndDate = future.getObservation().getMaturityDate();
    double fixingYearFraction = future.getObservation().getYearFraction();
    double convexity = hwProvider.futuresConvexityFactor(future.getLastTradeDate(), fixingStartDate, fixingEndDate);
    return convexity * forward - (1d - convexity) / fixingYearFraction;
  }