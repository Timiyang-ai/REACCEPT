  @Test
  public void test_putAll_collections() {
    Collection<LocalDate> dates = Arrays.asList(date(2013, 1, 1), date(2014, 1, 1));
    Collection<Double> values = Doubles.asList(2d, 3d);
    LocalDateDoubleTimeSeriesBuilder test = LocalDateDoubleTimeSeries.builder();
    test.putAll(dates, values);

    assertThat(test.get(date(2013, 1, 1))).hasValue(2d);
    assertThat(test.get(date(2014, 1, 1))).hasValue(3d);
  }