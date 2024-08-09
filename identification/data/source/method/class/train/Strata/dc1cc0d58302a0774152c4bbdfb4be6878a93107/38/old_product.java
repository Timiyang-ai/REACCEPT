public CurrencyAmount presentValue(IborFutureTrade trade, RatesProvider provider, double lastMarginPrice) {
    double referencePrice = referencePrice(trade, provider.getValuationDate(), lastMarginPrice);
    double price = price(trade, provider);
    return presentValue(trade, price, referencePrice);
  }