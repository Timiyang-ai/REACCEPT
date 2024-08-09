public MultiCurrencyAmount currencyExposure(ResolvedSwap swap, RatesProvider provider) {
    MultiCurrencyAmount ce = MultiCurrencyAmount.empty();
    for (ResolvedSwapLeg leg : swap.getLegs()) {
      ce = ce.plus(legPricer.currencyExposure(leg, provider));
    }
    return ce;
  }