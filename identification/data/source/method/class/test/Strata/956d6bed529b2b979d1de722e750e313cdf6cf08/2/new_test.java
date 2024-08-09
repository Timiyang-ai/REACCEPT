  @Test
  public void test_presentValueSensitivity_standard() {
    PointSensitivities point =
        PRICER.presentValueSensitivity(TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER);
    CurrencyParameterSensitivities computed = ISSUER_RATES_PROVIDER.parameterSensitivity(point)
        .combinedWith(RATES_PROVIDER.parameterSensitivity(point));
    CurrencyParameterSensitivities expected = FD_CAL.sensitivity(RATES_PROVIDER,
        p -> PRICER.presentValue(TRADE_STANDARD, p, ISSUER_RATES_PROVIDER)).combinedWith(
        FD_CAL.sensitivity(ISSUER_RATES_PROVIDER, p -> PRICER.presentValue(TRADE_STANDARD, RATES_PROVIDER, p)));
    assertThat(computed.equalWithTolerance(expected, NOTIONAL * QUANTITY * EPS)).isTrue();
  }