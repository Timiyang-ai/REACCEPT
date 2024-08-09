public PointSensitivities presentValueSensitivityRates(
      ResolvedFxVanillaOptionTrade trade,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    ResolvedFxVanillaOption product = trade.getProduct();
    PointSensitivities pvcsProduct =
        PRICER_PRODUCT.presentValueSensitivityRates(product, ratesProvider, volatilityProvider);
    Payment premium = trade.getPremium();
    PointSensitivities pvcsPremium = PRICER_PREMIUM.presentValueSensitivity(premium, ratesProvider).build();
    return pvcsProduct.combinedWith(pvcsPremium);
  }