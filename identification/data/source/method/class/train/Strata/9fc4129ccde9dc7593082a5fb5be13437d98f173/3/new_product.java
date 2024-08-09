public DoubleArray presentValueSensitivityModelParamsHullWhite(
      ResolvedIborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    ResolvedIborFuture product = trade.getProduct();
    DoubleArray hwSensi = productPricer.priceSensitivityModelParamsHullWhite(product, ratesProvider, hwProvider);
    hwSensi = hwSensi.multipliedBy(product.getNotional() * product.getAccrualFactor() * trade.getQuantity());
    return hwSensi;
  }