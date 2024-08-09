public ExplainMap explainPresentValue(SwapProduct product, RatesProvider provider) {
    ExpandedSwap swap = product.expand();

    ExplainMapBuilder builder = ExplainMap.builder();
    builder.put(ExplainKey.ENTRY_TYPE, "Swap");
    for (ExpandedSwapLeg leg : swap.getLegs()) {
      builder.addListEntryWithIndex(
          ExplainKey.LEGS, child -> legPricer.explainPresentValueInternal(leg, provider, child));
    }
    return builder.build();
  }