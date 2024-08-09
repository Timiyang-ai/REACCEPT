public Caffeine<K, V> ticker(Ticker ticker) {
    requireState(this.ticker == null);
    this.ticker = requireNonNull(ticker);
    return this;
  }