  @ParameterizedTest
  @MethodSource("data_adjusted")
  public void test_adjusted(LocalDate date, LocalDate expected) {
    AdjustableDate test = AdjustableDate.of(date, BDA_FOLLOW_SAT_SUN);
    assertThat(test.adjusted(REF_DATA)).isEqualTo(expected);
  }