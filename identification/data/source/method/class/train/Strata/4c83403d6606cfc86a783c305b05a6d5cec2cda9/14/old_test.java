  @Test
  public void test_presentValue() {
    MultiCurrencyAmount computed = PRICER.presentValue(FWD, PROVIDER);
    double expected1 = NOMINAL_USD * PROVIDER.discountFactor(USD, PAYMENT_DATE);
    double expected2 = -NOMINAL_USD * FX_RATE * PROVIDER.discountFactor(KRW, PAYMENT_DATE);
    assertThat(computed.getAmount(USD).getAmount()).isCloseTo(expected1, offset(NOMINAL_USD * TOL));
    assertThat(computed.getAmount(KRW).getAmount()).isCloseTo(expected2, offset(NOMINAL_USD * TOL));
  }