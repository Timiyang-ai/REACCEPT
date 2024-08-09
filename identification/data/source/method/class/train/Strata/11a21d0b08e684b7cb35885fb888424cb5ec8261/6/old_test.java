  @Test
  public void test_toString() {
    assertThat(CurrencyAmount.of(Currency.AUD, 100d).toString()).isEqualTo("AUD 100");
    assertThat(CurrencyAmount.of(Currency.AUD, 100.123d).toString()).isEqualTo("AUD 100.123");
  }