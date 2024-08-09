public Optional<TieredIdentity> nearest(List<TieredIdentity> identities) {
    Preconditions.checkState(!identities.isEmpty(), "No identities given");
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
      // Negative wait for a tier indicates that we should never return identities that do not match
      // in that tier.
      if (Configuration.containsKey(Template.LOCALITY_TIER_WAIT.format(tier.getTierName()))
          && Configuration.getInt(Template.LOCALITY_TIER_WAIT.format(tier.getTierName())) < 0) {
        return Optional.empty();
      }
    }
    return Optional.of(identities.get(0));
  }