public CurrencyAmount presentValue(ResolvedIborFutureTrade trade, RatesProvider ratesProvider, double lastSettlementPrice) {
    double referencePrice = referencePrice(trade, ratesProvider.getValuationDate(), lastSettlementPrice);
    double price = price(trade, ratesProvider);
    return presentValue(trade, price, referencePrice);
  }