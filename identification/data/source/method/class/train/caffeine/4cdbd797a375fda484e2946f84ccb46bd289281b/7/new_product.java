@Nonnull
  public Caffeine<K, V> ticker(@Nonnull Ticker ticker) {
    requireState(this.ticker == null);
    this.ticker = requireNonNull(ticker);
    return this;
  }