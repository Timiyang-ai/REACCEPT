public static double percentile(long[] counts, double p) {
    double[] pcts = {p};
    double[] results = new double[1];
    percentiles(counts, pcts, results);
    return results[0];
  }