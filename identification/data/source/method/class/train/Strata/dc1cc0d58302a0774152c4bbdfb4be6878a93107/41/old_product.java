public double price(IborFuture future, RatesProvider provider) {
    IborIndexRates rates = provider.iborIndexRates(future.getIndex());
    double forward = rates.rate(future.getFixingDate());
    return 1.0 - forward;
  }