public static double[] truncateSetInclusive(final double lower, final double upper, final double[] set) {
    // this is private, so assume inputs are fine
    final double[] temp = truncateSetExclusive(lower, upper, set);
    final int n = temp.length;
    if (n == 0) {
      return new double[] {lower, upper};
    }
    final boolean addLower = different(lower, temp[0]);
    final boolean addUpper = different(upper, temp[n - 1]);
    if (!addLower && !addUpper) { // replace first and last entries of set
      temp[0] = lower;
      temp[n - 1] = upper;
      return temp;
    }

    final int m = n + (addLower ? 1 : 0) + (addUpper ? 1 : 0);
    final double[] res = new double[m];
    System.arraycopy(temp, 0, res, (addLower ? 1 : 0), n);
    res[0] = lower;
    res[m - 1] = upper;

    return res;
  }