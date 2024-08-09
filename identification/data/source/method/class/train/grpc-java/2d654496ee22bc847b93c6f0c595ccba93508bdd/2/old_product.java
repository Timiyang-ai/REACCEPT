public Deadline minimum(Deadline other) {
    return isBefore(other) ? this : other;
  }