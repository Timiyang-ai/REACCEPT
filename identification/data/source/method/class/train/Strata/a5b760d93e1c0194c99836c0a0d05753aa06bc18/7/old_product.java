public MultiCurrencyAmount currencyExposure(SwapProduct product, RatesProvider provider) {
    ExpandedSwap swap = product.expand();
    MultiCurrencyAmount ce = MultiCurrencyAmount.empty();
    for (ExpandedSwapLeg leg : swap.getLegs()) {
      ce = ce.plus(legPricer.currencyExposure(leg, provider));
    }
    return ce;
  }