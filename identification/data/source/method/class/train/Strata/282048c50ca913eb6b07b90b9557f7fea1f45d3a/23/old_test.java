  @Test
  public void test_currentCash() {
    CurrencyAmount computed = LEG_PRICER.currentCash(FLOOR_LEG, RATES_PROVIDER, VOLATILITIES);
    assertThat(computed).isEqualTo(CurrencyAmount.zero(EUR));
  }