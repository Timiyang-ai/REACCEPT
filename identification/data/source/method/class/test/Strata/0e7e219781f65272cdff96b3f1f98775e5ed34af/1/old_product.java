public static int getLowerBoundIndex(double[] set, double value) {
    int n = set.length;
    if (value < set[0]) {
      return 0;
    }
    if (value > set[n - 1]) {
      return n - 1;
    }
    int index = Arrays.binarySearch(set, value);
    if (index >= 0) {
      // Fast break out if it's an exact match.
      return index;
    }
    if (index < 0) {
      index = -(index + 1);
      index--;
    }
    if (value == -0. && index < n - 1 && set[index + 1] == 0.) {
      ++index;
    }
    return index;
  }