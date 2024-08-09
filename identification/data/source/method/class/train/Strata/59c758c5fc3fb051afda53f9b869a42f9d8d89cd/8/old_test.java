  @Test
  public void test_presentValue() {
    CurrencyAmount pvTrade = PRICER_TRADE.presentValue(RDEPOSIT_TRADE, IMM_PROV);
    CurrencyAmount pvProduct = PRICER_PRODUCT.presentValue(RDEPOSIT_PRODUCT, IMM_PROV);
    assertThat(pvTrade.getCurrency()).isEqualTo(pvProduct.getCurrency());
    assertThat(pvTrade.getAmount()).isCloseTo(pvProduct.getAmount(), offset(TOLERANCE_PV));
  }