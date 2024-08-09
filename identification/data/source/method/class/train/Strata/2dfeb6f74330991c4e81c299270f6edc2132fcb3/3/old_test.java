  @ParameterizedTest
  @MethodSource("data_next")
  public void test_next(LocalDate date, LocalDate expectedNext, HolidayCalendar cal) {
    assertThat(cal.next(date)).isEqualTo(expectedNext);
  }