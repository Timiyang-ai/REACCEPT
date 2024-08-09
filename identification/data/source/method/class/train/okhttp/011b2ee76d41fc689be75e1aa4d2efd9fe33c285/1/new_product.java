public @Nullable String topPrivateDomain() {
    if (verifyAsIpAddress(host)) return null;
    return PublicSuffixDatabase.get().getEffectiveTldPlusOne(host);
  }