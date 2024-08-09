public MultiCurrencyAmount accruedInterest(ResolvedSwap swap, RatesProvider provider) {
    MultiCurrencyAmount result = MultiCurrencyAmount.empty();
    for (ResolvedSwapLeg leg : swap.getLegs()) {
      result = result.plus(legPricer.accruedInterest(leg, provider));
    }
    return result;
  }