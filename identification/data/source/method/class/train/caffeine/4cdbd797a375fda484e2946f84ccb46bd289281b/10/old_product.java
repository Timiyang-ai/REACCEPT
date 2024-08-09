public Caffeine<K, V> maximumWeight(long weight) {
    requireState(this.maximumWeight == UNSET_INT,
        "maximum weight was already set to %s", this.maximumWeight);
    requireState(this.maximumSize == UNSET_INT,
        "maximum size was already set to %s", this.maximumSize);
    this.maximumWeight = weight;
    requireArgument(weight >= 0, "maximum weight must not be negative");
    return this;
  }