  @ParameterizedTest
  @MethodSource("data_parseGood")
  public void test_parse_String_good(String input, Currency base, Currency counter, double rate) {
    assertThat(FxRate.parse(input)).isEqualTo(FxRate.of(base, counter, rate));
  }