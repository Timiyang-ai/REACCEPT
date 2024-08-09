  @Test
  public void test_presentValue() {
    CurrencyAmount computed = PRICER.presentValue(NDF, PROVIDER);
    double dscUsd = PROVIDER.discountFactor(USD, NDF.getPaymentDate());
    double dscKrw = PROVIDER.discountFactor(KRW, NDF.getPaymentDate());
    double expected = NOMINAL_USD * (dscUsd - dscKrw * FX_RATE / PROVIDER.fxRate(CurrencyPair.of(USD, KRW)));
    assertThat(computed.getCurrency()).isEqualTo(USD);
    assertThat(computed.getAmount()).isCloseTo(expected, offset(NOMINAL_USD * TOL));
  }