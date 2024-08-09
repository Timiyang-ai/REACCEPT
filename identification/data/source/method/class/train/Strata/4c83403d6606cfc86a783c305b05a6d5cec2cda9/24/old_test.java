  @Test
  public void test_currentCash_zero() {
    CurrencyAmount computed = PRICER.currentCash(NDF, PROVIDER);
    assertThat(computed).isEqualTo(CurrencyAmount.zero(NDF.getSettlementCurrency()));
  }