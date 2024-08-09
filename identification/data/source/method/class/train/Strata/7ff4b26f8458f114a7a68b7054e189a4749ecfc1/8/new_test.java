  @ParameterizedTest
  @MethodSource("data_next")
  public void test_next(LocalDate date, LocalDate expectedNext) {
    HolidayCalendar test = new MockHolCal();
    assertThat(test.next(date)).isEqualTo(expectedNext);
  }