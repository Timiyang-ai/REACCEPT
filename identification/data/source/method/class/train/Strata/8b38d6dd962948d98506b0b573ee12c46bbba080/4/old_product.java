public double price(
      IborFuture future,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    double parRate = parRate(future, ratesProvider, hwProvider);
    return 1d - parRate;
  }