@Nonnull
  public Caffeine<K, V> weakKeys() {
    requireState(keyStrength == null, "Key strength was already set to %s", keyStrength);
    keyStrength = Strength.WEAK;
    return this;
  }