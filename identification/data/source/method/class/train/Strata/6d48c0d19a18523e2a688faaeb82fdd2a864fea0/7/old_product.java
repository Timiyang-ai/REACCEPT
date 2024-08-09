public MultiCurrencyAmount currencyExposure(
      ResolvedFxSingleBarrierOptionTrade trade,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {

    Payment premium = trade.getPremium();
    CurrencyAmount pvPremium = PRICER_PREMIUM.presentValue(premium, ratesProvider);
    ResolvedFxSingleBarrierOption product = trade.getProduct();
    return PRICER_PRODUCT.currencyExposure(product, ratesProvider, volatilityProvider).plus(pvPremium);
  }