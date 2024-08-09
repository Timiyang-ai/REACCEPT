public Tenor normalized() {
    Period norm = period.normalized();
    return (norm != period ? Tenor.of(norm) : this);
  }