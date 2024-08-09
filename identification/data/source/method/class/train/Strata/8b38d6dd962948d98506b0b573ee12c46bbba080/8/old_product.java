public double price(
      IborFuture future,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantConvexityFactorProvider hwProvider) {

    double parRate = parRate(future, ratesProvider, hwProvider);
    return 1d - parRate;
  }