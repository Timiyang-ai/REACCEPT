public PointSensitivities parSpreadSensitivity(IborFixingDepositTrade trade, RatesProvider provider) {
    return PRODUCT_PRICER.parSpreadSensitivity(trade.getProduct(), provider);
  }