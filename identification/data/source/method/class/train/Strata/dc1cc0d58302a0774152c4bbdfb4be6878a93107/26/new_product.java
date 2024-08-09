public double price(RatesProvider provider, IborFutureTrade trade) {
    return productPricer.price(provider, trade.getSecurity().getProduct());
  }