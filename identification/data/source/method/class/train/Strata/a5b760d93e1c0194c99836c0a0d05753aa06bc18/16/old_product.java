public MultiCurrencyAmount currentCash(SwapProduct product, RatesProvider provider) {
    ExpandedSwap swap = product.expand();
    MultiCurrencyAmount ce = MultiCurrencyAmount.empty();
    for (ExpandedSwapLeg leg : swap.getLegs()) {
      ce = ce.plus(legPricer.currentCash(leg, provider));
    }
    return ce;
  }