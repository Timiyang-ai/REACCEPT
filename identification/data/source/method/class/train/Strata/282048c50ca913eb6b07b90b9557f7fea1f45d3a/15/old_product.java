public MultiCurrencyAmount currentCash(
      CmsProduct cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    ExpandedCms expanded = cms.expand();
    CurrencyAmount ccCmsLeg = cmsLegPricer.currentCash(expanded.getCmsLeg(), ratesProvider, swaptionVolatilities);
    if (!expanded.getPayLeg().isPresent()) {
      return MultiCurrencyAmount.of(ccCmsLeg);
    }
    CurrencyAmount ccPayLeg = payLegPricer.currentCash(expanded.getPayLeg().get(), ratesProvider);
    return MultiCurrencyAmount.of(ccPayLeg).plus(ccCmsLeg);
  }