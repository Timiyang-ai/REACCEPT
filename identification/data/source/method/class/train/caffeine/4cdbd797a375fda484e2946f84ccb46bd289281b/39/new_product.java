@Nonnull
  public Caffeine<K, V> weakKeys() {
    requireState(keyStrength == null, "Key strength was already set to %s", keyStrength);
    requireState(writer == null, "Weak keys may not be used with CacheWriter");

    keyStrength = Strength.WEAK;
    return this;
  }