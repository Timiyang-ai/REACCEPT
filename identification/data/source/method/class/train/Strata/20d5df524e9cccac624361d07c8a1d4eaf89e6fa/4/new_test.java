  @Test
  public void test_currencyExposureFromCleanPrice() {
    MultiCurrencyAmount computed = PRICER.currencyExposureFromCleanPrice(
        TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER, REF_DATA, TRADE_PRICE);
    PointSensitivities point = PRICER.presentValueSensitivityFromCleanPrice(
        TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER, REF_DATA, TRADE_PRICE);
    MultiCurrencyAmount expected = RATES_PROVIDER.currencyExposure(point).plus(
        PRICER.presentValueFromCleanPrice(TRADE_STANDARD, RATES_PROVIDER, ISSUER_RATES_PROVIDER, REF_DATA, TRADE_PRICE));
    assertThat(computed.getAmounts()).hasSize(1);
    assertThat(computed.getAmount(USD).getAmount()).isCloseTo(expected.getAmount(USD).getAmount(), offset(NOTIONAL * QUANTITY * TOL));
  }