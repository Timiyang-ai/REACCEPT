public MultiCurrencyAmount currencyExposure(
      ResolvedFxVanillaOptionTrade trade,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    Payment premium = trade.getPremium();
    CurrencyAmount pvPremium = PRICER_PREMIUM.presentValue(premium, ratesProvider);
    ResolvedFxVanillaOption product = trade.getProduct();
    return PRICER_PRODUCT.currencyExposure(product, ratesProvider, volatilityProvider).plus(pvPremium);
  }