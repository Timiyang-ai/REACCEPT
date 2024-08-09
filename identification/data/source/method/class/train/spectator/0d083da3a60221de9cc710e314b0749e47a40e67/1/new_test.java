  @Test
  public void percentiles() {
    long[] counts = new long[PercentileBuckets.length()];
    for (int i = 0; i < 100_000; ++i) {
      ++counts[PercentileBuckets.indexOf(i)];
    }

    double[] pcts = new double[] {0.0, 25.0, 50.0, 75.0, 90.0, 95.0, 98.0, 99.0, 99.5, 100.0};
    double[] results = new double[pcts.length];

    PercentileBuckets.percentiles(counts, pcts, results);

    double[] expected = new double[] {0.0, 25e3, 50e3, 75e3, 90e3, 95e3, 98e3, 99e3, 99.5e3, 100e3};
    double threshold = 0.1 * 100_000; // quick check, should be within 10% of total
    Assertions.assertArrayEquals(expected, results, threshold);

    // Further check each value is within 10% of actual percentile
    for (int i = 0 ; i < results.length; ++i) {
      threshold = 0.1 * expected[i] + 1e-12;
      Assertions.assertEquals(expected[i], results[i], threshold);
    }
  }