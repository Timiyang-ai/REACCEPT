public CurrencyAmount forecastValue(ResolvedSwapLeg leg, RatesProvider provider) {
    return CurrencyAmount.of(leg.getCurrency(), forecastValueInternal(leg, provider));
  }