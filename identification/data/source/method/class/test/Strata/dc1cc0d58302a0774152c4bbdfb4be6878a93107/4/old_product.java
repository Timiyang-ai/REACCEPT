public double price(IborFuture future, RatesProvider provider) {
    double forward = provider.iborIndexRate(future.getIndex(), future.getFixingDate());
    return 1.0 - forward;
  }