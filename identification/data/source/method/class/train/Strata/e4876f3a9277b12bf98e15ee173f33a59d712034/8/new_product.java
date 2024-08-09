@Override
  public Set<? extends SimpleMarketDataKey<?>> requirements() {
    return ImmutableSet.of(fxNearKey, fxPtsKey);
  }