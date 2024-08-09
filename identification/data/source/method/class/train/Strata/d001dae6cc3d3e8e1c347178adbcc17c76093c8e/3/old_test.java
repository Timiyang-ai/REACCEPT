  @Test
  public void test_currentCash() {
    CurrencyAmount computed = PRICER.currentCash(PRODUCT, RATES_PROVIDER, VALUATION);
    assertThat(computed.getAmount()).isEqualTo(0d);
  }