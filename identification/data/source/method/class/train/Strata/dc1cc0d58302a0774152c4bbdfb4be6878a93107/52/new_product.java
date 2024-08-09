public CurrencyAmount presentValue(ResolvedIborFutureTrade trade, RatesProvider provider, double settlementPrice) {
    double referencePrice = referencePrice(trade, provider.getValuationDate(), settlementPrice);
    double price = price(trade, provider);
    return presentValue(trade, price, referencePrice);
  }