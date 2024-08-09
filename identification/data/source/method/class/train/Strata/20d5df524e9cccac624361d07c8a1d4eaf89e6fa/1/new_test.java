  @Test
  public void test_currencyExposureFromCleanPriceWithZSpread() {
    MultiCurrencyAmount computed = PRICER.currencyExposureFromCleanPriceWithZSpread(
        TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER, REF_DATA, TRADE_PRICE, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR);
    PointSensitivities point = PRICER.presentValueSensitivityFromCleanPriceWithZSpread(
        TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER, REF_DATA, TRADE_PRICE, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR);
    MultiCurrencyAmount expected = RATES_PROVIDER.currencyExposure(point).plus(
        PRICER.presentValueFromCleanPriceWithZSpread(TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER,
            REF_DATA, TRADE_PRICE, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR));
    assertThat(computed.getAmounts()).hasSize(1);
    assertThat(computed.getAmount(USD).getAmount()).isCloseTo(expected.getAmount(USD).getAmount(), offset(NOTIONAL * QUANTITY * TOL));
  }