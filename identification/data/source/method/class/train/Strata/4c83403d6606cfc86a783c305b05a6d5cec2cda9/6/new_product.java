public MultiCurrencyAmount presentValue(
      ResolvedFxSingleBarrierOptionTrade trade,
      RatesProvider ratesProvider,
      BlackFxOptionVolatilities volatilities) {

    ResolvedFxSingleBarrierOption product = trade.getProduct();
    CurrencyAmount pvProduct = productPricer.presentValue(product, ratesProvider, volatilities);
    Payment premium = trade.getPremium();
    CurrencyAmount pvPremium = paymentPricer.presentValue(premium, ratesProvider);
    return MultiCurrencyAmount.of(pvProduct, pvPremium);
  }