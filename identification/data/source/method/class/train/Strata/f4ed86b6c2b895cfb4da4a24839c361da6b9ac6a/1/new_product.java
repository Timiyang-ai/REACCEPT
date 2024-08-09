@Override
  public ResolvedSwap resolve(ReferenceData refData) {
    // avoid streams as profiling showed a hotspot
    // most efficient to loop around legs once
    ImmutableList.Builder<ResolvedSwapLeg> resolvedLegs = ImmutableList.builder();
    ImmutableSet.Builder<Currency> currencies = ImmutableSet.builder();
    ImmutableSet.Builder<Index> indices = ImmutableSet.builder();
    for (SwapLeg leg : legs) {
      ResolvedSwapLeg resolvedLeg = leg.resolve(refData);
      resolvedLegs.add(resolvedLeg);
      currencies.add(resolvedLeg.getCurrency());
      leg.collectIndices(indices);
    }
    return new ResolvedSwap(resolvedLegs.build(), currencies.build(), indices.build());
  }