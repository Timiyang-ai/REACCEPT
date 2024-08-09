public CurrencyAmount presentValue(IborFixingDepositTrade trade, ImmutableRatesProvider provider) {
    return productPricer.presentValue(trade.getProduct(), provider);
  }