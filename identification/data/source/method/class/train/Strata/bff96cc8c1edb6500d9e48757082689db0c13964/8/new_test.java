  @ParameterizedTest
  @MethodSource("data_lastBusinessDayOfMonth")
  public void test_lastBusinessDayOfMonth(LocalDate date, LocalDate expectedEom) {
    HolidayCalendar test = new MockEomHolCal();
    assertThat(test.lastBusinessDayOfMonth(date)).isEqualTo(expectedEom);
  }