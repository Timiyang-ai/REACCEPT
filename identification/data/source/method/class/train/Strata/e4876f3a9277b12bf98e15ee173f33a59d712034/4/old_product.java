@Override
  public Set<? extends MarketDataKey<?>> requirements() {
    // TODO: extra key for near forward points
    return ImmutableSet.of(farForwardPointsKey, fxKey());
  }