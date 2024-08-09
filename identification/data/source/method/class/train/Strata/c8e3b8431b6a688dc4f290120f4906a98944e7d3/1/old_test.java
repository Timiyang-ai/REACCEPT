  @ParameterizedTest
  @MethodSource("data_yearFraction")
  public void test_relativeYearFraction(
      DayCount dayCount, int y1, int m1, int d1, int y2, int m2, int d2, Double value) {
    double expected = (value == SIMPLE_30_360 ? calc360(y1, m1, d1, y2, m2, d2) : value);
    LocalDate date1 = LocalDate.of(y1, m1, d1);
    LocalDate date2 = LocalDate.of(y2, m2, d2);
    assertThat(dayCount.relativeYearFraction(date1, date2)).isEqualTo(expected);
  }