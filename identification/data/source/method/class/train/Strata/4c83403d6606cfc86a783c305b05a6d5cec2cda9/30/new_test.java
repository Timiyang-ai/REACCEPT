  @Test
  public void test_currentCash_zero() {
    assertThat(PRICER_TRADE.currentCash(OPTION_TRADE, VAL_DATE)).isEqualTo(CurrencyAmount.zero(PREMIUM.getCurrency()));
  }