public CurrencyAmount currentCash(SwapLeg leg, RatesProvider provider) {
    ExpandedSwapLeg expanded = leg.expand();
    return CurrencyAmount.of(leg.getCurrency(),
        currentCashPeriodsInternal(expanded, provider) + (currentCashEventsInternal(expanded, provider)));
  }