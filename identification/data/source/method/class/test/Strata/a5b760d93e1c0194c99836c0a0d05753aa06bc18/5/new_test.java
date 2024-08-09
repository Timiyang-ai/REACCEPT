  @Test
  public void test_currentCash_zero() {
    ResolvedSwapLeg expSwapLeg = IBOR_SWAP_LEG_REC_GBP;
    CurrencyAmount computed = PRICER_LEG.currentCash(expSwapLeg, RATES_GBP);
    assertThat(computed).isEqualTo(CurrencyAmount.zero(expSwapLeg.getCurrency()));
  }