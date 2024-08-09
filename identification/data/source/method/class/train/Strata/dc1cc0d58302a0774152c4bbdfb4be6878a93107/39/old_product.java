public double price(ResolvedIborFuture future, RatesProvider provider) {
    IborIndexRates rates = provider.iborIndexRates(future.getIndex());
    double forward = rates.rate(future.getObservation());
    return 1.0 - forward;
  }