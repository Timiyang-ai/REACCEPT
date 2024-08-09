public double parSpread(IborFixingDepositTrade trade, RatesProvider provider) {
    return PRODUCT_PRICER.parSpread(trade.getProduct(), provider);
  }