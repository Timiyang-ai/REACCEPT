  @ParameterizedTest
  @MethodSource("data_shift")
  public void test_shift(LocalDate date, int amount, LocalDate expected) {
    // 16th, 18th, Sat, Sun
    HolidayCalendar test = new MockHolCal();
    assertThat(test.shift(date, amount)).isEqualTo(expected);
  }