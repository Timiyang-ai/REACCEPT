  @Test
  public void test_currencyExposureWithZSpread() {
    MultiCurrencyAmount computed = PRICER.currencyExposureWithZSpread(
        TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR);
    PointSensitivities point = PRICER.presentValueSensitivityWithZSpread(
        TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR);
    MultiCurrencyAmount expected = RATES_PROVIDER.currencyExposure(point).plus(PRICER.presentValueWithZSpread(
        TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR));
    assertThat(computed.getAmounts()).hasSize(1);
    assertThat(computed.getAmount(USD).getAmount()).isCloseTo(expected.getAmount(USD).getAmount(), offset(NOTIONAL * QUANTITY * TOL));
  }