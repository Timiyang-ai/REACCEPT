public DoubleArray presentValueSensitivityHullWhiteParameter(
      ResolvedIborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    ResolvedIborFuture product = trade.getProduct();
    DoubleArray hwSensi = productPricer.priceSensitivityHullWhiteParameter(product, ratesProvider, hwProvider);
    hwSensi = hwSensi.multipliedBy(product.getNotional() * product.getAccrualFactor() * trade.getQuantity());
    return hwSensi;
  }