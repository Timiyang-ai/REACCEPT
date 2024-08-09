public double price(RatesProvider provider, IborFuture future) {
    double forward = provider.iborIndexRate(future.getIndex(), future.getFixingDate());
    return 1.0 - forward;
  }