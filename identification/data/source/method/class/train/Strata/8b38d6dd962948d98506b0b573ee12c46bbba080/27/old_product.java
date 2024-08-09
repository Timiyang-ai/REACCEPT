public double convexityAdjustment(
      ResolvedIborFuture future,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    double forward = ratesProvider.iborIndexRates(future.getIndex()).rate(future.getObservation());
    double parRate = parRate(future, ratesProvider, hwProvider);
    return forward - parRate;
  }