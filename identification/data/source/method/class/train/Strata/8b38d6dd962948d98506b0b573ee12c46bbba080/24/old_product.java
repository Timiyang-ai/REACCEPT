public double parRate(
      IborFuture future,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    double forward = ratesProvider.iborIndexRates(future.getIndex()).rate(future.getFixingDate());
    IborIndex index = future.getIndex();
    LocalDate fixingStartDate = index.calculateEffectiveFromFixing(future.getFixingDate());
    LocalDate fixingEndDate = index.calculateMaturityFromEffective(fixingStartDate);
    double fixingYearFraction = index.getDayCount().yearFraction(fixingStartDate, fixingEndDate);
    double convexity = hwProvider.futuresConvexityFactor(future.getLastTradeDate(), fixingStartDate, fixingEndDate);
    return convexity * forward - (1d - convexity) / fixingYearFraction;
  }