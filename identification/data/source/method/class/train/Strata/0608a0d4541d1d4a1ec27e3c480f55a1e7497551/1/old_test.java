  @ParameterizedTest
  @MethodSource("data_parseGood")
  public void test_parse_String_good(String input, Currency base, Currency counter) {
    assertThat(CurrencyPair.parse(input)).isEqualTo(CurrencyPair.of(base, counter));
  }