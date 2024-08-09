public double parSpread(RatesProvider provider, IborFutureTrade trade, double referencePrice) {
    return price(provider, trade) - referencePrice;
  }