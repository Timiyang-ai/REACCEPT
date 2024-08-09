  @Test
  public void test_currencyExposure() {
    MultiCurrencyAmount computed = PRICER.currencyExposure(PRODUCT, RATES_PROVIDER, ISSUER_RATES_PROVIDER, VALUATION);
    PointSensitivities point = PRICER.presentValueSensitivity(PRODUCT, RATES_PROVIDER, ISSUER_RATES_PROVIDER).build();
    MultiCurrencyAmount expected = RATES_PROVIDER.currencyExposure(point)
        .plus(PRICER.presentValue(PRODUCT, RATES_PROVIDER, ISSUER_RATES_PROVIDER));
    assertThat(computed.getCurrencies()).hasSize(1);
    assertThat(computed.getAmount(USD).getAmount()).isCloseTo(expected.getAmount(USD).getAmount(), offset(NOTIONAL * TOL));
  }