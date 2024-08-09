public CurrencyAmount presentValue(IborFixingDepositTrade trade, RatesProvider provider) {
    return productPricer.presentValue(trade.getProduct(), provider);
  }