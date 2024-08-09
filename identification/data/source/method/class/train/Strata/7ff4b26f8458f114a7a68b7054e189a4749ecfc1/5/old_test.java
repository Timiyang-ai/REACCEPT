  @ParameterizedTest
  @MethodSource("data_shift")
  public void test_adjustBy(LocalDate date, int amount, LocalDate expected) {
    HolidayCalendar test = new MockHolCal();
    assertThat(date.with(test.adjustBy(amount))).isEqualTo(expected);
  }