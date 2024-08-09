public double parSpread(IborFutureTrade trade, RatesProvider provider, double referencePrice) {
    return price(trade, provider) - referencePrice;
  }