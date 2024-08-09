  @Test
  public void test_currencyExposure() {
    PointSensitivities point = PRICER.presentValueSensitivityRates(FUTURE_TRADE, RATE_PROVIDER, HW_PROVIDER);
    MultiCurrencyAmount expected = RATE_PROVIDER.currencyExposure(point)
        .plus(PRICER.presentValue(FUTURE_TRADE, RATE_PROVIDER, HW_PROVIDER, LAST_PRICE));
    MultiCurrencyAmount computed = PRICER.currencyExposure(FUTURE_TRADE, RATE_PROVIDER, HW_PROVIDER, LAST_PRICE);
    assertThat(computed.size()).isEqualTo(1);
    assertThat(computed.getAmount(EUR).getAmount()).isCloseTo(expected.getAmount(EUR).getAmount(), offset(NOTIONAL * QUANTITY * TOL));
  }