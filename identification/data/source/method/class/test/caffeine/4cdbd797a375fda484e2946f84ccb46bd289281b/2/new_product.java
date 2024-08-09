@NonNull
  public Caffeine<K, V> maximumWeight(long maximumWeight) {
    requireState(this.maximumWeight == UNSET_INT,
        "maximum weight was already set to %s", this.maximumWeight);
    requireState(this.maximumSize == UNSET_INT,
        "maximum size was already set to %s", this.maximumSize);
    this.maximumWeight = maximumWeight;
    requireArgument(maximumWeight >= 0, "maximum weight must not be negative");
    return this;
  }