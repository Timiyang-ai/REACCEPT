public PointSensitivities presentValueSensitivity(
      FxVanillaOptionTrade trade,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    FxVanillaOption product = trade.getProduct();
    PointSensitivities pvcsProduct =
        PRICER_PRODUCT.presentValueSensitivity(product, ratesProvider, volatilityProvider);
    Payment premium = trade.getPremium();
    PointSensitivities pvcsPremium = PRICER_PREMIUM.presentValueSensitivity(premium, ratesProvider).build();
    return pvcsProduct.combinedWith(pvcsPremium);
  }