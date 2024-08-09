public CurrencyAmount presentValue(RatesProvider provider, IborFutureTrade trade, double referencePrice) {
    double price = price(provider, trade);
    return presentValue(trade, price, referencePrice);
  }