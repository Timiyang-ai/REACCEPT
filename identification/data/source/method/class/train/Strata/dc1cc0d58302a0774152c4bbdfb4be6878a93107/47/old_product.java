public PointSensitivities parSpreadSensitivity(PricingEnvironment env, IborFutureTrade trade) {
    return productPricer.priceSensitivity(env, trade.getSecurity().getProduct());
  }