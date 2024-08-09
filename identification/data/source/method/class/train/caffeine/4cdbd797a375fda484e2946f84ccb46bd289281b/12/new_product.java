@Nonnull
  public Caffeine<K, V> ticker(@Nonnull Ticker ticker) {
    requireState(this.ticker == null, "Ticker was already set to %s", this.ticker);
    this.ticker = requireNonNull(ticker);
    return this;
  }