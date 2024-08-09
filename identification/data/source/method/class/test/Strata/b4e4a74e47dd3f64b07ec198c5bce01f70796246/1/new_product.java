@Override
  public int compareTo(Currency other) {
    // hash code is unique and ordered so can be used for compareTo
    return cachedHashCode - other.cachedHashCode;
  }