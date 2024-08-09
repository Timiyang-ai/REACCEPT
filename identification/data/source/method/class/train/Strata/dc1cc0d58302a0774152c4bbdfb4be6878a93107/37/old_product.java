public CurrencyAmount presentValue(IborFutureTrade trade, RatesProvider provider, double referencePrice) {
    double price = price(trade, provider);
    return presentValue(trade, price, referencePrice);
  }