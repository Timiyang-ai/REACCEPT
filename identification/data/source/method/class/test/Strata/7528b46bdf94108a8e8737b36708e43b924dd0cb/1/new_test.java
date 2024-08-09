  @Test
  public void test_allCurrencies() {
    Swap test = Swap.of(MOCK_GBP1, MOCK_USD1);
    assertThat(test.allCurrencies()).containsOnly(GBP, USD, EUR);
  }