@Override
  public int compareTo(LocalDateDoublePoint other) {
    return ComparisonChain.start()
        .compare(date, other.date)
        .compare(value, other.value)
        .result();
  }