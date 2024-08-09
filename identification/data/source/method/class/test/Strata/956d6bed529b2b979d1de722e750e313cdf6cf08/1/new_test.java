  @Test
  public void test_currencyExposureWithZSpread() {
    MultiCurrencyAmount computed = PRICER.currencyExposureWithZSpread(
        PRODUCT, RATES_PROVIDER, ISSUER_RATES_PROVIDER, VALUATION, Z_SPREAD, CONTINUOUS, 0);
    PointSensitivities point = PRICER.presentValueSensitivityWithZSpread(
        PRODUCT, RATES_PROVIDER, ISSUER_RATES_PROVIDER, Z_SPREAD, CONTINUOUS, 0).build();
    MultiCurrencyAmount expected = RATES_PROVIDER.currencyExposure(point).plus(
        PRICER.presentValueWithZSpread(PRODUCT, RATES_PROVIDER, ISSUER_RATES_PROVIDER, Z_SPREAD, CONTINUOUS, 0));
    assertThat(computed.getCurrencies()).hasSize(1);
    assertThat(computed.getAmount(USD).getAmount()).isCloseTo(expected.getAmount(USD).getAmount(), offset(NOTIONAL * TOL));
  }