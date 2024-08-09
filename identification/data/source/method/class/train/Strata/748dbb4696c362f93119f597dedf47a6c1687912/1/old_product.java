@Override
  public int compareTo(LocalDateDoublePoint other) {
    int cmp = date.compareTo(other.date);
    if (cmp == 0) {
      cmp = Double.compare(value, other.value);
    }
    return cmp;
  }