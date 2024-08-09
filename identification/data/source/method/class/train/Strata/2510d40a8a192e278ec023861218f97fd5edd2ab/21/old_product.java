public CurrencyAmount presentValue(RatesProvider provider, SwapLeg leg) {
    return CurrencyAmount.of(leg.getCurrency(), presentValueInternal(provider, leg));
  }