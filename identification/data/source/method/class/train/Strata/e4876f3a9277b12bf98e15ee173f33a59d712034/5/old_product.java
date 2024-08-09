@Override
  public Set<? extends SimpleMarketDataKey<?>> requirements() {
    return ImmutableSet.of(spreadKey, fxKey());
  }