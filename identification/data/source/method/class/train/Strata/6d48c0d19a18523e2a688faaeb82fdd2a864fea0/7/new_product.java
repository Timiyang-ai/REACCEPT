public MultiCurrencyAmount currencyExposure(
      ResolvedFxSingleBarrierOptionTrade trade,
      RatesProvider ratesProvider,
      BlackFxOptionVolatilities volatilities) {

    Payment premium = trade.getPremium();
    CurrencyAmount pvPremium = paymentPricer.presentValue(premium, ratesProvider);
    ResolvedFxSingleBarrierOption product = trade.getProduct();
    return productPricer.currencyExposure(product, ratesProvider, volatilities).plus(pvPremium);
  }