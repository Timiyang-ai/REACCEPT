  @Test
  public void test_currentCash() {
    CurrencyAmount computed = PRICER.currentCash(TRADE_SETTLED, RATES_PROVIDER_ON_PAY);
    CurrencyAmount expected = PRODUCT_PRICER.currentCash(RPRODUCT, RATES_PROVIDER_ON_PAY, SETTLEMENT_BEFORE);
    assertThat(computed.getAmount()).isCloseTo(expected.getAmount(), offset(NOTIONAL * QUANTITY * TOL));
  }