public CurrencyAmount presentValue(
      ResolvedDsfTrade trade,
      RatesProvider provider,
      double referencePrice) {

    double price = price(trade, provider);
    return presentValue(trade, price, referencePrice);
  }