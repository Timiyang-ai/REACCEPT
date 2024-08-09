public MultiCurrencyAmount currencyExposure(
      CmsProduct cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    ExpandedCms expanded = cms.expand();
    CurrencyAmount ceCmsLeg = cmsLegPricer.presentValue(expanded.getCmsLeg(), ratesProvider, swaptionVolatilities);
    if (!expanded.getPayLeg().isPresent()) {
      return MultiCurrencyAmount.of(ceCmsLeg);
    }
    MultiCurrencyAmount cePayLeg = payLegPricer.currencyExposure(expanded.getPayLeg().get(), ratesProvider);
    return cePayLeg.plus(ceCmsLeg);
  }