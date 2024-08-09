  @ParameterizedTest
  @MethodSource("data_lastBusinessDayOfMonth")
  public void test_isLastBusinessDayOfMonth(LocalDate date, LocalDate expectedEom) {
    assertThat(HOLCAL_END_MONTH.isLastBusinessDayOfMonth(date)).isEqualTo(date.equals(expectedEom));
  }