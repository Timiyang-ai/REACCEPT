public MultiCurrencyAmount currencyExposure(
      SwaptionProduct swaption,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    return MultiCurrencyAmount.of(presentValue(swaption, ratesProvider, hwProvider));
  }