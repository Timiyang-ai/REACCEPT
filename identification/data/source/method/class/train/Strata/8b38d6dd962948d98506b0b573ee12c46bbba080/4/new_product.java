public double price(
      ResolvedIborFuture future,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    double parRate = parRate(future, ratesProvider, hwProvider);
    return 1d - parRate;
  }