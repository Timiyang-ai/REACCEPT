public ExplainMap explainPresentValue(ResolvedSwap swap, RatesProvider provider) {
    ExplainMapBuilder builder = ExplainMap.builder();
    builder.put(ExplainKey.ENTRY_TYPE, "Swap");
    for (ResolvedSwapLeg leg : swap.getLegs()) {
      builder.addListEntryWithIndex(
          ExplainKey.LEGS, child -> legPricer.explainPresentValueInternal(leg, provider, child));
    }
    return builder.build();
  }