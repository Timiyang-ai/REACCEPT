  @Test
  public void test_presentValueSensitivityFromCleanPriceWithZSpread_standard() {
    PointSensitivities point = PRICER.presentValueSensitivityFromCleanPriceWithZSpread(
        TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER, REF_DATA, TRADE_PRICE, Z_SPREAD, CONTINUOUS, 0);
    CurrencyParameterSensitivities computed = ISSUER_RATES_PROVIDER.parameterSensitivity(point)
        .combinedWith(RATES_PROVIDER.parameterSensitivity(point));
    CurrencyParameterSensitivities expected = FD_CAL.sensitivity(RATES_PROVIDER,
        p -> PRICER.presentValueFromCleanPriceWithZSpread(
            TRADE_STANDARD, p, ISSUER_RATES_PROVIDER, REF_DATA, TRADE_PRICE, Z_SPREAD, CONTINUOUS, 0))
        .combinedWith(FD_CAL.sensitivity(ISSUER_RATES_PROVIDER,
            p -> PRICER.presentValueFromCleanPriceWithZSpread(
                TRADE_STANDARD, RATES_PROVIDER, p, REF_DATA, TRADE_PRICE, Z_SPREAD, CONTINUOUS, 0)));
    assertThat(computed.equalWithTolerance(expected, NOTIONAL * QUANTITY * EPS)).isTrue();
  }