public CurrencyAmount currentCash(ResolvedSwapLeg leg, RatesProvider provider) {
    return CurrencyAmount.of(leg.getCurrency(),
        currentCashPeriodsInternal(leg, provider) + (currentCashEventsInternal(leg, provider)));
  }