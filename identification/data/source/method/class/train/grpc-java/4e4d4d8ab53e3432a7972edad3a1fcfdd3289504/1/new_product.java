@Override
  public int compareTo(Deadline that) {
    checkTicker(that);
    long diff = this.deadlineNanos - that.deadlineNanos;
    if (diff < 0) {
      return -1;
    } else if (diff > 0) {
      return 1;
    }
    return 0;
  }