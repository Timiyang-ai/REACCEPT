public MultiCurrencyAmount presentValue(
      CmsTrade cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    MultiCurrencyAmount pvCms = productPricer.presentValue(cms.getProduct(), ratesProvider, swaptionVolatilities);
    if (!cms.getPremium().isPresent()) {
      return pvCms;
    }
    CurrencyAmount pvPremium = paymentPricer.presentValue(cms.getPremium().get(), ratesProvider);
    return pvCms.plus(pvPremium);
  }