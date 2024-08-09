public CurrencyAmount presentValue(ResolvedSwapLeg leg, RatesProvider provider) {
    return CurrencyAmount.of(leg.getCurrency(), presentValueInternal(leg, provider));
  }