public PointSensitivities presentValueSensitivity(IborFixingDepositTrade trade, RatesProvider provider) {
    return PRODUCT_PRICER.presentValueSensitivity(trade.getProduct(), provider);
  }