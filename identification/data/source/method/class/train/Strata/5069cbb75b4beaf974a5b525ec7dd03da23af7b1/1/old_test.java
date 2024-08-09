  @Test
  public void getCurrencies() {
    assertThat(VALUES_ARRAY.getCurrencies()).containsExactlyInAnyOrder(Currency.GBP, Currency.USD, Currency.EUR);
  }