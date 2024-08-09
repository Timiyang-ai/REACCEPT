public Optional<TieredIdentity> nearest(List<TieredIdentity> identities) {
    if (identities.isEmpty()) {
      return Optional.empty();
    }
    for (LocalityTier tier : mTiers) {
      for (TieredIdentity identity : identities) {
        for (LocalityTier otherTier : identity.mTiers) {
          if (tier.mTierName.equals(otherTier.mTierName)
              && tier.mValue != null
              && tier.mValue.equals(otherTier.mValue)) {
            return Optional.of(identity);
          }
        }
      }
    }
    return Optional.of(identities.get(0));
  }