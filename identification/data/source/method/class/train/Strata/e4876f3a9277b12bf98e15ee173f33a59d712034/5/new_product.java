@Override
  public Set<? extends MarketDataKey<?>> requirements() {
    return ImmutableSet.of(spreadKey, fxKey());
  }