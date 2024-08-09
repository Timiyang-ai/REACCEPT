public double convexityAdjustment(
      IborFuture future,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantConvexityFactorProvider hwProvider) {

    double forward = ratesProvider.iborIndexRates(future.getIndex()).rate(future.getFixingDate());
    double parRate = parRate(future, ratesProvider, hwProvider);
    return forward - parRate;
  }