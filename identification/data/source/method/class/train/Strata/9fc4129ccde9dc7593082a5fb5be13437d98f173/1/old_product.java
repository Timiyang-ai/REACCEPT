public DoubleArray presentValueSensitivityHullWhiteParameter(
      IborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {
    IborFuture product = trade.getSecurity().getProduct();
    DoubleArray hwSensi = productPricer.priceSensitivityHullWhiteParameter(product, ratesProvider, hwProvider);
    hwSensi = hwSensi.multipliedBy(product.getNotional() * product.getAccrualFactor() * trade.getQuantity());
    return hwSensi;
  }