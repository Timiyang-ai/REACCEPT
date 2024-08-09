public MultiCurrencyAmount currencyExposure(
      ResolvedCms cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    CurrencyAmount ceCmsLeg = cmsLegPricer.presentValue(cms.getCmsLeg(), ratesProvider, swaptionVolatilities);
    if (!cms.getPayLeg().isPresent()) {
      return MultiCurrencyAmount.of(ceCmsLeg);
    }
    MultiCurrencyAmount cePayLeg = payLegPricer.currencyExposure(cms.getPayLeg().get(), ratesProvider);
    return cePayLeg.plus(ceCmsLeg);
  }