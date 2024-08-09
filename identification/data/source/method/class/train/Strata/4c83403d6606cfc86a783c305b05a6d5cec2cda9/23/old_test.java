  @Test
  public void test_presentValue_beforeStart() {
    MultiCurrencyAmount computed = PRICER.presentValue(SWAP_PRODUCT, PROVIDER);
    double expectedUsd = NOMINAL_USD *
        (PROVIDER.discountFactor(USD, PAYMENT_DATE_NEAR) - PROVIDER.discountFactor(USD, PAYMENT_DATE_FAR));
    double expectedKrw = NOMINAL_USD *
        (-FX_RATE * PROVIDER.discountFactor(KRW, PAYMENT_DATE_NEAR)
        + (FX_RATE + FX_FWD_POINTS) * PROVIDER.discountFactor(KRW, PAYMENT_DATE_FAR));
    assertThat(computed.getAmount(USD).getAmount()).isCloseTo(expectedUsd, offset(NOMINAL_USD * TOL));
    assertThat(computed.getAmount(KRW).getAmount()).isCloseTo(expectedKrw, offset(NOMINAL_USD * FX_RATE * TOL));

    // currency exposure
    MultiCurrencyAmount exposure = PRICER.currencyExposure(SWAP_PRODUCT, PROVIDER);
    assertThat(exposure).isEqualTo(computed);
  }