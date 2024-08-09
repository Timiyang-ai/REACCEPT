  @ParameterizedTest
  @MethodSource("data_lastBusinessDayOfMonth")
  public void test_lastBusinessDayOfMonth(LocalDate date, LocalDate expectedEom) {
    assertThat(HOLCAL_END_MONTH.lastBusinessDayOfMonth(date)).isEqualTo(expectedEom);
  }