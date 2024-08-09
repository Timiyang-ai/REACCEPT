public static double[] getIntegrationsPoints(
      double start,
      double end,
      DoubleArray discountCurveNodes,
      DoubleArray creditCurveNodes) {

    double[] set1 = truncateSetExclusive(start, end, discountCurveNodes.toArray());
    double[] set2 = truncateSetExclusive(start, end, creditCurveNodes.toArray());
    int n1 = set1.length;
    int n2 = set2.length;
    int n = n1 + n2;
    double[] set = new double[n];
    System.arraycopy(set1, 0, set, 0, n1);
    System.arraycopy(set2, 0, set, n1, n2);
    Arrays.sort(set);

    double[] temp = new double[n + 2];
    temp[0] = start;
    int pos = 0;
    for (int i = 0; i < n; i++) {
      if (different(temp[pos], set[i])) {
        temp[++pos] = set[i];
      }
    }
    if (different(temp[pos], end)) {
      pos++;
    }
    temp[pos] = end; // add the end point (this may replace the last entry in temp if that is not significantly different)

    int resLength = pos + 1;
    if (resLength == n + 2) {
      return temp; // everything was unique
    }

    double[] res = new double[resLength];
    System.arraycopy(temp, 0, res, 0, resLength);
    return res;
  }