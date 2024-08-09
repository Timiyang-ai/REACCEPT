public MultiCurrencyAmount currentCash(
      CmsTrade cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    MultiCurrencyAmount ccCms = productPricer.currentCash(cms.getProduct(), ratesProvider, swaptionVolatilities);
    if (!cms.getPremium().isPresent()) {
      return ccCms;
    }
    Payment premium = cms.getPremium().get();
    if (premium.getDate().equals(ratesProvider.getValuationDate())) {
      ccCms = ccCms.plus(premium.getCurrency(), premium.getAmount());
    }
    return ccCms;
  }