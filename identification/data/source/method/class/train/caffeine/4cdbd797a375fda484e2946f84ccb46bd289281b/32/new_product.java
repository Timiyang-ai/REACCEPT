@NonNull
  public Caffeine<K, V> maximumSize(@NonNegative long maximumSize) {
    requireState(this.maximumSize == UNSET_INT,
        "maximum size was already set to %s", this.maximumSize);
    requireState(this.maximumWeight == UNSET_INT,
        "maximum weight was already set to %s", this.maximumWeight);
    requireState(this.weigher == null, "maximum size can not be combined with weigher");
    requireArgument(maximumSize >= 0, "maximum size must not be negative");
    this.maximumSize = maximumSize;
    return this;
  }