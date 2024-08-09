@Override
  public int compareTo(Deadline that) {
    assert this.ticker == that.ticker : "Tickers don't match";
    long diff = this.deadlineNanos - that.deadlineNanos;
    if (diff < 0) {
      return -1;
    } else if (diff > 0) {
      return 1;
    }
    return 0;
  }