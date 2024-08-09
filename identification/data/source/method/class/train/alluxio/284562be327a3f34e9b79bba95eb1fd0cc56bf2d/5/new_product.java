public Optional<TieredIdentity> nearest(List<TieredIdentity> identities) {
    if (identities.isEmpty()) {
      return Optional.empty();
    }
    for (LocalityTier tier : mTiers) {
      for (TieredIdentity identity : identities) {
        for (LocalityTier otherTier : identity.mTiers) {
          if (tier != null && tier.matches(otherTier)) {
            return Optional.of(identity);
          }
        }
      }
    }
    return Optional.of(identities.get(0));
  }