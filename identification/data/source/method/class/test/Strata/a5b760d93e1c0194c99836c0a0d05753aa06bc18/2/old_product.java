public CurrencyAmount forecastValue(SwapLeg leg, RatesProvider provider) {
    return CurrencyAmount.of(leg.getCurrency(), forecastValueInternal(leg, provider));
  }