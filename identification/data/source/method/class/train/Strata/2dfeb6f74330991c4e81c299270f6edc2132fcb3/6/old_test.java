  @ParameterizedTest
  @MethodSource("data_previous")
  public void test_previous(LocalDate date, LocalDate expectedPrevious, HolidayCalendar cal) {
    assertThat(cal.previous(date)).isEqualTo(expectedPrevious);
  }