@Test(expectedExceptions = IllegalArgumentException.class)
  public void test_putAll_collectionsMismatch() {
    LocalDateDoubleTimeSeriesBuilder test = LocalDateDoubleTimeSeries.builder();
    test.putAll(Arrays.asList(date(2014, 1, 1)), Doubles.asList(2d, 3d));
  }