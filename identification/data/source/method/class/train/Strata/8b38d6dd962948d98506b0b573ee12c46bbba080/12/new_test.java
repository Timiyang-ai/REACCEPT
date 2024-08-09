  @Test
  public void test_parSpread() {
    double computed = PRICER.parSpread(FUTURE_TRADE, RATE_PROVIDER, HW_PROVIDER, LAST_PRICE);
    CurrencyAmount pv = PRICER.presentValue(FUTURE_TRADE, RATE_PROVIDER, HW_PROVIDER, LAST_PRICE + computed);
    assertThat(pv.getAmount()).isCloseTo(0d, offset(TOL * NOTIONAL * QUANTITY));
  }