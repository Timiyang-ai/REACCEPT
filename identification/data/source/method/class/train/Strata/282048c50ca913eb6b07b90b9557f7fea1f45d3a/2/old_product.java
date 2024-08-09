public MultiCurrencyAmount currencyExposure(
      CmsTrade cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    MultiCurrencyAmount ceCms = productPricer.currencyExposure(cms.getProduct(), ratesProvider, swaptionVolatilities);
    if (!cms.getPremium().isPresent()) {
      return ceCms;
    }
    CurrencyAmount pvPremium = paymentPricer.presentValue(cms.getPremium().get(), ratesProvider);
    return ceCms.plus(pvPremium);
  }