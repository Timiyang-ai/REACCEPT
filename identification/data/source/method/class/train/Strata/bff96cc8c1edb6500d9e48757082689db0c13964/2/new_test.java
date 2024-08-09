  @ParameterizedTest
  @MethodSource("data_nextOrSame")
  public void test_nextOrSame(LocalDate date, LocalDate expectedNext) {
    HolidayCalendar test = new MockHolCal();
    assertThat(test.nextOrSame(date)).isEqualTo(expectedNext);
  }