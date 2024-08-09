public DoubleArray priceSensitivityHullWhiteParameter(
      ResolvedIborFuture future,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    IborIndexObservation obs = future.getIborRate().getObservation();
    double forward = ratesProvider.iborIndexRates(future.getIndex()).rate(obs);
    LocalDate fixingStartDate = obs.getEffectiveDate();
    LocalDate fixingEndDate = obs.getMaturityDate();
    double fixingYearFraction = obs.getYearFraction();
    DoubleArray convexityDeriv = hwProvider.futuresConvexityFactorAdjoint(
        future.getLastTradeDate(), fixingStartDate, fixingEndDate).getDerivatives();
    convexityDeriv = convexityDeriv.multipliedBy(-forward - 1d / fixingYearFraction);
    return convexityDeriv;
  }