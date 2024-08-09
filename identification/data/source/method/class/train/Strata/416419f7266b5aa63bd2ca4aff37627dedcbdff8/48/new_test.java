  @Test
  public void test_currentCash_zero() {
    CurrencyAmount ccComputed = TRADE_PRICER.currentCash(TRADE, VAL_DATE);
    assertThat(ccComputed).isEqualTo(CurrencyAmount.zero(EUR));
  }