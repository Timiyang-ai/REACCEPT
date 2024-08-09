public double percentile(double p) {
    long[] counts = new long[PercentileBuckets.length()];
    for (int i = 0; i < counts.length; ++i) {
      counts[i] = counterFor(i).count();
    }
    double v = PercentileBuckets.percentile(counts, p);
    return v / 1e9;
  }