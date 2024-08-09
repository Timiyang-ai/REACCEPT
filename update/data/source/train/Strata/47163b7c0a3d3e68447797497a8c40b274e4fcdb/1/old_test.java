@Test(expectedExceptions = IllegalArgumentException.class)
  public void test_putAll_arraysMismatch() {
    LocalDateDoubleTimeSeriesBuilder test = LocalDateDoubleTimeSeries.builder();
    LocalDate[] dates = {date(2014, 1, 1)};
    double[] values = {2d, 3d};
    test.putAll(dates, values);
  }