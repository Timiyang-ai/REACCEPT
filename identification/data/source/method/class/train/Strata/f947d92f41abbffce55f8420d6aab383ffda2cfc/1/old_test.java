  @Test
  public void test_parse_String_roundTrip() {
    assertThat(CurrencyAmount.parse(CCY_AMOUNT.toString())).isEqualTo(CCY_AMOUNT);
  }