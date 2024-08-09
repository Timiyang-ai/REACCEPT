  @ParameterizedTest
  @MethodSource("data_previousOrSame")
  public void test_previousOrSame(LocalDate date, LocalDate expectedPrevious) {
    HolidayCalendar test = new MockHolCal();
    assertThat(test.previousOrSame(date)).isEqualTo(expectedPrevious);
  }