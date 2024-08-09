  @Test
  public void test_presentValueSensitivityWithZSpread_standard() {
    PointSensitivities point = PRICER.presentValueSensitivityWithZSpread(
        TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR);
    CurrencyParameterSensitivities computed = ISSUER_RATES_PROVIDER.parameterSensitivity(point)
        .combinedWith(RATES_PROVIDER.parameterSensitivity(point));
    CurrencyParameterSensitivities expected = FD_CAL.sensitivity(RATES_PROVIDER, p -> PRICER.presentValueWithZSpread(
        TRADE_STANDARD, p, ISSUER_RATES_PROVIDER, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR))
        .combinedWith(FD_CAL.sensitivity(ISSUER_RATES_PROVIDER, p -> PRICER.presentValueWithZSpread(
            TRADE_STANDARD, RATES_PROVIDER, p, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR)));
    assertThat(computed.equalWithTolerance(expected, NOTIONAL * QUANTITY * EPS)).isTrue();
  }