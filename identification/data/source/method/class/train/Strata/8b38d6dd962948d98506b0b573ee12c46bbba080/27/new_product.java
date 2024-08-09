public double convexityAdjustment(
      ResolvedIborFuture future,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    IborIndexObservation obs = future.getIborRate().getObservation();
    double forward = ratesProvider.iborIndexRates(future.getIndex()).rate(obs);
    double parRate = parRate(future, ratesProvider, hwProvider);
    return forward - parRate;
  }