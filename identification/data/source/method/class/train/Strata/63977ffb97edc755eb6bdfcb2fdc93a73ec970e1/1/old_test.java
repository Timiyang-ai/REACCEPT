  @ParameterizedTest
  @MethodSource("data_adjust")
  public void test_adjust(RollConvention conv, LocalDate input, LocalDate expected) {
    assertThat(conv.adjust(input)).isEqualTo(expected);
  }