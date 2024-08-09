public static int getLowerBoundIndex(DoubleArray set, double value) {
    int n = set.size();
    if (value < set.get(0)) {
      return 0;
    }
    if (value > set.get(n - 1)) {
      return n - 1;
    }
    int index = Arrays.binarySearch(set.toArrayUnsafe(), value);
    if (index >= 0) {
      // Fast break out if it's an exact match.
      return index;
    }
    index = -(index + 1);
    index--;
    if (value == -0. && index < n - 1 && set.get(index + 1) == 0.) {
      ++index;
    }
    return index;
  }