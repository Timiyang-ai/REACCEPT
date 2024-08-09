public PointSensitivities presentValueSensitivityModelParamsVolatility(
      ResolvedFxSingleBarrierOptionTrade trade,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    ResolvedFxSingleBarrierOption product = trade.getProduct();
    return PRICER_PRODUCT.presentValueSensitivityModelParamsVolatility(product, ratesProvider, volatilityProvider).build();
  }