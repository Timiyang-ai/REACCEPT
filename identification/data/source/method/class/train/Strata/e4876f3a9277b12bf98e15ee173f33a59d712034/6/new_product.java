@Override
  public Set<? extends MarketDataId<?>> requirements() {
    return ImmutableSet.of(fxRateId, spreadId);
  }