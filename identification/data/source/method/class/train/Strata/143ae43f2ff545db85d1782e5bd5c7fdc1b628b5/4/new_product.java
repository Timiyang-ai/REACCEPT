public DoubleArray priceSensitivityHullWhiteParameter(
      IborFuture future,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    double forward = ratesProvider.iborIndexRates(future.getIndex()).rate(future.getFixingDate());
    IborIndex index = future.getIndex();
    LocalDate fixingStartDate = index.calculateEffectiveFromFixing(future.getFixingDate());
    LocalDate fixingEndDate = index.calculateMaturityFromEffective(fixingStartDate);
    double fixingYearFraction = index.getDayCount().yearFraction(fixingStartDate, fixingEndDate);
    DoubleArray convexityDeriv = hwProvider.futuresConvexityFactorAdjoint(
        future.getLastTradeDate(), fixingStartDate, fixingEndDate).getDerivatives();
    convexityDeriv = convexityDeriv.multipliedBy(-forward - 1d / fixingYearFraction);
    return convexityDeriv;
  }