  @Test
  public void test_contains_Currency() {
    CurrencyPair test = CurrencyPair.of(GBP, USD);
    assertThat(test.contains(GBP)).isEqualTo(true);
    assertThat(test.contains(USD)).isEqualTo(true);
    assertThat(test.contains(EUR)).isEqualTo(false);
  }