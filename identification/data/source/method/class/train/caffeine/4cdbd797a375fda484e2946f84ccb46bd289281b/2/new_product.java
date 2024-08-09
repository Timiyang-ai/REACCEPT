@Nonnull
  public Caffeine<K, V> weakValues() {
    requireState(valueStrength == null, "Value strength was already set to %s", valueStrength);
    valueStrength = Strength.WEAK;
    return this;
  }