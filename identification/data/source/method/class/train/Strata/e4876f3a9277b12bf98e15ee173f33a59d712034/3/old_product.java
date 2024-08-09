@Override
  public Set<? extends MarketDataId<?>> requirements() {
    // TODO: extra identifier for near forward points
    return ImmutableSet.of(farForwardPointsId, fxId());
  }