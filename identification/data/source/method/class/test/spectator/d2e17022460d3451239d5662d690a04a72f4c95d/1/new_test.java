  @Test
  public void percentile() {
    long[] counts = new long[PercentileBuckets.length()];
    for (int i = 0; i < 100_000; ++i) {
      ++counts[PercentileBuckets.indexOf(i)];
    }

    double[] pcts = new double[] {0.0, 25.0, 50.0, 75.0, 90.0, 95.0, 98.0, 99.0, 99.5, 100.0};
    for (int i = 0 ; i < pcts.length; ++i) {
      double expected = pcts[i] * 1e3;
      double threshold = 0.1 * expected + 1e-12;
      Assertions.assertEquals(expected, PercentileBuckets.percentile(counts, pcts[i]), threshold);
    }
  }