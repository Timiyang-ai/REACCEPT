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
      if (Configuration.containsKey(Template.LOCALITY_TIER_STRICT.format(tier.getTierName()))
          && Configuration.getBoolean(Template.LOCALITY_TIER_STRICT.format(tier.getTierName()))) {
        return Optional.empty();
      }
    }
    return Optional.of(identities.get(0));
  }