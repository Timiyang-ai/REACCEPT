public MultiCurrencyAmount currencyExposure(
      ResolvedFxVanillaOptionTrade trade,
      RatesProvider ratesProvider,
      BlackFxOptionVolatilities volatilities) {

    Payment premium = trade.getPremium();
    CurrencyAmount pvPremium = paymentPricer.presentValue(premium, ratesProvider);
    ResolvedFxVanillaOption product = trade.getProduct();
    return productPricer.currencyExposure(product, ratesProvider, volatilities).plus(pvPremium);
  }