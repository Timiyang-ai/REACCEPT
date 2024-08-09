public double price(ResolvedIborFuture future, RatesProvider ratesProvider) {
    IborIndexRates rates = ratesProvider.iborIndexRates(future.getIndex());
    double forward = rates.rate(future.getIborRate().getObservation());
    return 1.0 - forward;
  }