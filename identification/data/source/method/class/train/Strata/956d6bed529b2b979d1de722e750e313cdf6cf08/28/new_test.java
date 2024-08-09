  @Test
  public void test_currencyExposure() {
    MultiCurrencyAmount computed =
        PRICER.currencyExposure(TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER);
    PointSensitivities point = PRICER.presentValueSensitivity(
        TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER);
    MultiCurrencyAmount expected = RATES_PROVIDER.currencyExposure(point).plus(
        PRICER.presentValue(TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER));
    assertThat(computed.getAmounts()).hasSize(1);
    assertThat(computed.getAmount(USD).getAmount()).isCloseTo(expected.getAmount(USD).getAmount(), offset(NOTIONAL * QUANTITY * TOL));
  }