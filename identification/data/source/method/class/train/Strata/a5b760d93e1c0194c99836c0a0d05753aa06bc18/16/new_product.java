public MultiCurrencyAmount currentCash(ResolvedSwap swap, RatesProvider provider) {
    MultiCurrencyAmount ce = MultiCurrencyAmount.empty();
    for (ResolvedSwapLeg leg : swap.getLegs()) {
      ce = ce.plus(legPricer.currentCash(leg, provider));
    }
    return ce;
  }