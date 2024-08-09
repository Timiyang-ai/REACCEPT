  @ParameterizedTest
  @MethodSource("data_roll")
  public void test_toRollConvention(
      StubConvention conv, LocalDate start, LocalDate end, Frequency freq, boolean eom, RollConvention expected) {
    assertThat(conv.toRollConvention(start, end, freq, eom)).isEqualTo(expected);
  }