  @ParameterizedTest
  @MethodSource("data_lastBusinessDayOfMonth")
  public void test_isLastBusinessDayOfMonth(LocalDate date, LocalDate expectedEom) {
    HolidayCalendar test = new MockEomHolCal();
    assertThat(test.isLastBusinessDayOfMonth(date)).isEqualTo(date.equals(expectedEom));
  }