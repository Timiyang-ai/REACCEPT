  @ParameterizedTest
  @MethodSource("data_previous")
  public void test_previous(LocalDate date, LocalDate expectedPrevious) {
    HolidayCalendar test = new MockHolCal();
    assertThat(test.previous(date)).isEqualTo(expectedPrevious);
  }