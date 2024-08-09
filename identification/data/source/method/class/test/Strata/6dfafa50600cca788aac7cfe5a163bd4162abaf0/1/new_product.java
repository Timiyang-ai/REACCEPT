public CurrencyAmount presentValue(
      ResolvedDsfTrade trade,
      RatesProvider ratesProvider,
      double lastSettlementPrice) {

    double price = price(trade, ratesProvider);
    double referencePrice = referencePrice(trade, ratesProvider.getValuationDate(), lastSettlementPrice);
    return presentValue(trade, price, referencePrice);
  }