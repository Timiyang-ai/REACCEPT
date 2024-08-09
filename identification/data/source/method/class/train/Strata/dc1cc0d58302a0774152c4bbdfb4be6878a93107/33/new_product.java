public double price(IborFutureTrade trade, RatesProvider provider) {
    return productPricer.price(trade.getSecurity().getProduct(), provider);
  }