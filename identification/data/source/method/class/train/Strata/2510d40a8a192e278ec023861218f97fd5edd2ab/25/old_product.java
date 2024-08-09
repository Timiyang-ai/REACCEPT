public CurrencyAmount presentValue(SwapLeg leg, RatesProvider provider) {
    return CurrencyAmount.of(leg.getCurrency(), presentValueInternal(leg, provider));
  }