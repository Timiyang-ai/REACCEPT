static DoubleArray truncateSetInclusive(double lower, double upper, DoubleArray set) {

    double[] temp = truncateSetExclusive(lower, upper, set.toArray());
    int n = temp.length;
    if (n == 0) {
      return DoubleArray.of(lower, upper);
    }
    boolean addLower = different(lower, temp[0]);
    boolean addUpper = different(upper, temp[n - 1]);
    if (!addLower && !addUpper) { // replace first and last entries of set
      temp[0] = lower;
      temp[n - 1] = upper;
      return DoubleArray.ofUnsafe(temp);
    }

    int m = n + (addLower ? 1 : 0) + (addUpper ? 1 : 0);
    double[] res = new double[m];
    System.arraycopy(temp, 0, res, (addLower ? 1 : 0), n);
    res[0] = lower;
    res[m - 1] = upper;

    return DoubleArray.ofUnsafe(res);
  }