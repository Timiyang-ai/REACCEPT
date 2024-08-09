public PointSensitivityBuilder presentValueSensitivityBlackVolatility(
      FxVanillaOptionTrade trade,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    FxVanillaOption product = trade.getProduct();
    return PRICER_PRODUCT.presentValueSensitivityBlackVolatility(product, ratesProvider, volatilityProvider);
  }