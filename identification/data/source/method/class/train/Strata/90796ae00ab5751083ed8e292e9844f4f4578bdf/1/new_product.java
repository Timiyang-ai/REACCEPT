public MultiCurrencyAmount currencyExposure(
      ResolvedSwaption swaption,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    return MultiCurrencyAmount.of(presentValue(swaption, ratesProvider, hwProvider));
  }