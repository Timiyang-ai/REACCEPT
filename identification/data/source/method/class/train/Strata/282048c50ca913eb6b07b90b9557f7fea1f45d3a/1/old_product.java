public MultiCurrencyAmount presentValue(
      ResolvedCms cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    CurrencyAmount pvCmsLeg = cmsLegPricer.presentValue(cms.getCmsLeg(), ratesProvider, swaptionVolatilities);
    if (!cms.getPayLeg().isPresent()) {
      return MultiCurrencyAmount.of(pvCmsLeg);
    }
    CurrencyAmount pvPayLeg = payLegPricer.presentValue(cms.getPayLeg().get(), ratesProvider);
    return MultiCurrencyAmount.of(pvCmsLeg).plus(pvPayLeg);
  }