public PointSensitivityBuilder presentValueSensitivityBlackVolatility(
      ResolvedFxSingleBarrierOptionTrade trade,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    ResolvedFxSingleBarrierOption product = trade.getProduct();
    return PRICER_PRODUCT.presentValueSensitivityVolatility(product, ratesProvider, volatilityProvider);
  }