public MultiCurrencyAmount accruedInterest(SwapProduct product, RatesProvider provider) {
    ExpandedSwap swap = product.expand();
    MultiCurrencyAmount result = MultiCurrencyAmount.empty();
    for (ExpandedSwapLeg leg : swap.getLegs()) {
      result = result.plus(legPricer.accruedInterest(leg, provider));
    }
    return result;
  }