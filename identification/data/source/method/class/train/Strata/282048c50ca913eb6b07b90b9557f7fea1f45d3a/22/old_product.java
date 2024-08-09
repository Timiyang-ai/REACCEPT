public MultiCurrencyAmount presentValue(
      CmsProduct cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    ExpandedCms expanded = cms.expand();
    CurrencyAmount pvCmsLeg = cmsLegPricer.presentValue(expanded.getCmsLeg(), ratesProvider, swaptionVolatilities);
    if (!expanded.getPayLeg().isPresent()) {
      return MultiCurrencyAmount.of(pvCmsLeg);
    }
    CurrencyAmount pvPayLeg = payLegPricer.presentValue(expanded.getPayLeg().get(), ratesProvider);
    return MultiCurrencyAmount.of(pvCmsLeg).plus(pvPayLeg);
  }