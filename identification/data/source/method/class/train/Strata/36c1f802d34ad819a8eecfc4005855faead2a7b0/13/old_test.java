  @Test
  public void test_presentValue_provider() {
    CurrencyAmount computed = PRICER.presentValue(PAYMENT, PROVIDER);
    double expected = NOTIONAL_USD * DF;
    assertThat(computed.getAmount()).isCloseTo(expected, offset(NOTIONAL_USD * TOL));
  }