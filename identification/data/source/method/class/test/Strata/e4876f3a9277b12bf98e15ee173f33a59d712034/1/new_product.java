@Override
  public Set<? extends SimpleMarketDataKey<?>> requirements() {
    // TODO: extra key for near forward points
    return ImmutableSet.of(farForwardPointsKey, fxKey());
  }