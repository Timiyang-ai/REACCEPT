  @ParameterizedTest
  @MethodSource("data_subSeries")
  public void test_subSeries(LocalDate start, LocalDate end, int[] expected) {
    LocalDateDoubleTimeSeries base = LocalDateDoubleTimeSeries.builder().putAll(DATES_2010_14, VALUES_10_14).build();
    LocalDateDoubleTimeSeries test = base.subSeries(start, end);
    assertThat(test.size()).isEqualTo(expected.length);
    for (int i = 0; i < DATES_2010_14.size(); i++) {
      if (Arrays.binarySearch(expected, i) >= 0) {
        assertThat(test.get(DATES_2010_14.get(i))).isEqualTo(OptionalDouble.of(VALUES_10_14.get(i)));
      } else {
        assertThat(test.get(DATES_2010_14.get(i))).isEqualTo(OptionalDouble.empty());
      }
    }
  }