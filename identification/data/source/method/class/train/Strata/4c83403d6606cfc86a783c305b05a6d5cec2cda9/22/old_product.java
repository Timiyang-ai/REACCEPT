public PointSensitivities presentValueSensitivity(
      ResolvedFxVanillaOptionTrade trade,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    ResolvedFxVanillaOption product = trade.getProduct();
    PointSensitivities pvcsProduct =
        PRICER_PRODUCT.presentValueSensitivity(product, ratesProvider, volatilityProvider);
    Payment premium = trade.getPremium();
    PointSensitivities pvcsPremium = PRICER_PREMIUM.presentValueSensitivity(premium, ratesProvider).build();
    return pvcsProduct.combinedWith(pvcsPremium);
  }