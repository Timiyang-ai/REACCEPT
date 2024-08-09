  @ParameterizedTest
  @MethodSource("data_shift")
  public void test_shift(LocalDate date, int amount, LocalDate expected) {
    assertThat(HOLCAL_MON_WED.shift(date, amount)).isEqualTo(expected);
  }