  @Test
  public void test_presentValueSensitivityFromCleanPrice_standard() {
    PointSensitivities point = PRICER.presentValueSensitivityFromCleanPrice(
        TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER, REF_DATA, TRADE_PRICE);
    CurrencyParameterSensitivities computed = ISSUER_RATES_PROVIDER.parameterSensitivity(point)
        .combinedWith(RATES_PROVIDER.parameterSensitivity(point));
    CurrencyParameterSensitivities expected = FD_CAL.sensitivity(RATES_PROVIDER,
        p -> PRICER.presentValueFromCleanPrice(TRADE_STANDARD, p, ISSUER_RATES_PROVIDER, REF_DATA, TRADE_PRICE))
        .combinedWith(FD_CAL.sensitivity(ISSUER_RATES_PROVIDER,
            p -> PRICER.presentValueFromCleanPrice(TRADE_STANDARD, RATES_PROVIDER, p, REF_DATA, TRADE_PRICE)));
    assertThat(computed.equalWithTolerance(expected, NOTIONAL * QUANTITY * EPS)).isTrue();
  }