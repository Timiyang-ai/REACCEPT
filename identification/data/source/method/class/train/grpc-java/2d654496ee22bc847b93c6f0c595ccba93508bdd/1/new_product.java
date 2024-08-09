public Deadline minimum(Deadline other) {
    checkTicker(other);
    return isBefore(other) ? this : other;
  }