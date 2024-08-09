public CurrencyAmount presentValue(IborFixingDepositTrade trade, RatesProvider provider) {
    return PRODUCT_PRICER.presentValue(trade.getProduct(), provider);
  }