  @Test
  public void test_currentCash_zero() {
    MultiCurrencyAmount computed = SWAP_PRODUCT_PRICER.currentCash(SWAP_CROSS_CURRENCY, RATES_GBP_USD);
    assertThat(computed).isEqualTo(MultiCurrencyAmount.of(CurrencyAmount.zero(GBP), CurrencyAmount.zero(USD)));
    MultiCurrencyAmount fromTrade = SWAP_TRADE_PRICER.currentCash(SWAP_TRADE_CROSS_CURRENCY, RATES_GBP_USD);
    assertThat(fromTrade).isEqualTo(computed);
  }