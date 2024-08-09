public Deadline minimum(Deadline other) {
    assert this.ticker == other.ticker : "Tickers don't match";
    return isBefore(other) ? this : other;
  }