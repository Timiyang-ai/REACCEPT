public PointSensitivities presentValueSensitivityModelParamsVolatility(
      ResolvedFxVanillaOptionTrade trade,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    ResolvedFxVanillaOption product = trade.getProduct();
    return PRICER_PRODUCT.presentValueSensitivityModelParamsVolatility(product, ratesProvider, volatilityProvider).build();
  }