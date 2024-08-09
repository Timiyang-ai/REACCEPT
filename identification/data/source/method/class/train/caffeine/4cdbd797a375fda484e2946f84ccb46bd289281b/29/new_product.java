@Nonnull
  public Caffeine<K, V> softValues() {
    requireState(valueStrength == null, "Value strength was already set to %s", valueStrength);
    valueStrength = Strength.SOFT;
    return this;
  }