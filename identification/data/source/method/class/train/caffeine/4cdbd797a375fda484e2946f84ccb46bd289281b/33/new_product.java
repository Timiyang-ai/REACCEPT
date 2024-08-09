@Nonnull
  public Caffeine<K, V> initialCapacity(@Nonnegative int initialCapacity) {
    requireState(this.initialCapacity == UNSET_INT,
        "initial capacity was already set to %s", this.initialCapacity);
    requireArgument(initialCapacity >= 0);
    this.initialCapacity = initialCapacity;
    return this;
  }