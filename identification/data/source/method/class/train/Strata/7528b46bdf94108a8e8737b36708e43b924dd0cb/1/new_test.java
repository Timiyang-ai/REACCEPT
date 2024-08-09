  @Test
  public void test_allPaymentCurrencies() {
    Swap test = Swap.of(MOCK_GBP1, MOCK_USD1);
    assertThat(test.allPaymentCurrencies()).containsOnly(GBP, USD);
  }