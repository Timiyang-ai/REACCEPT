public MultiCurrencyAmount currentCash(
      ResolvedCms cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    CurrencyAmount ccCmsLeg = cmsLegPricer.currentCash(cms.getCmsLeg(), ratesProvider, swaptionVolatilities);
    if (!cms.getPayLeg().isPresent()) {
      return MultiCurrencyAmount.of(ccCmsLeg);
    }
    CurrencyAmount ccPayLeg = payLegPricer.currentCash(cms.getPayLeg().get(), ratesProvider);
    return MultiCurrencyAmount.of(ccPayLeg).plus(ccCmsLeg);
  }