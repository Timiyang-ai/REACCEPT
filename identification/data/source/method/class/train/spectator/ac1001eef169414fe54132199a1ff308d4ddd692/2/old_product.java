public double percentile(double p) {
    long[] counts = new long[PercentileBuckets.length()];
    for (int i = 0; i < counts.length; ++i) {
      counts[i] = counters[i].count();
    }
    return PercentileBuckets.percentile(counts, p);
  }