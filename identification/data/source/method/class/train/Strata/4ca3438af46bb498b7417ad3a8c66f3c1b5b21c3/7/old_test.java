  @Test
  public void test_currentCash_zero() {
    assertThat(PRICER_TRADE.currentCash(RFRA_TRADE, RATES_PROVIDER)).isEqualTo(CurrencyAmount.zero(FRA.getCurrency()));
  }