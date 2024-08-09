public DoubleArray priceSensitivityHullWhiteParameter(
      ResolvedIborFuture future,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    double forward = ratesProvider.iborIndexRates(future.getIndex()).rate(future.getObservation());
    LocalDate fixingStartDate = future.getObservation().getEffectiveDate();
    LocalDate fixingEndDate = future.getObservation().getMaturityDate();
    double fixingYearFraction = future.getObservation().getYearFraction();
    DoubleArray convexityDeriv = hwProvider.futuresConvexityFactorAdjoint(
        future.getLastTradeDate(), fixingStartDate, fixingEndDate).getDerivatives();
    convexityDeriv = convexityDeriv.multipliedBy(-forward - 1d / fixingYearFraction);
    return convexityDeriv;
  }