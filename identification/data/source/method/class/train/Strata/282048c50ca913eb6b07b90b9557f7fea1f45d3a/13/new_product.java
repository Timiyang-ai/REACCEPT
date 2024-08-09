public MultiCurrencyAmount presentValue(
      ResolvedCmsTrade trade,
      RatesProvider ratesProvider,
      SabrSwaptionVolatilities swaptionVolatilities) {

    MultiCurrencyAmount pvCms = productPricer.presentValue(trade.getProduct(), ratesProvider, swaptionVolatilities);
    if (!trade.getPremium().isPresent()) {
      return pvCms;
    }
    CurrencyAmount pvPremium = paymentPricer.presentValue(trade.getPremium().get(), ratesProvider);
    return pvCms.plus(pvPremium);
  }