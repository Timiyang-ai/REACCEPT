public PointSensitivityBuilder presentValueSensitivityBlackVolatility(
      ResolvedFxVanillaOptionTrade trade,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    ResolvedFxVanillaOption product = trade.getProduct();
    return PRICER_PRODUCT.presentValueSensitivityBlackVolatility(product, ratesProvider, volatilityProvider);
  }