public CurrencyAmount presentValue(ResolvedIborFixingDepositTrade trade, ImmutableRatesProvider provider) {
    return productPricer.presentValue(trade.getProduct(), provider);
  }