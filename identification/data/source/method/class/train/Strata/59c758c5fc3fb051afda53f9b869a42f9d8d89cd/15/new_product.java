public CurrencyAmount presentValue(ResolvedIborFixingDepositTrade trade, RatesProvider provider) {
    return productPricer.presentValue(trade.getProduct(), provider);
  }