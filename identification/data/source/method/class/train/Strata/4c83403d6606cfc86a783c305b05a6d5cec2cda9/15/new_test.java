  @Test
  public void test_currencyExposure() {
    MultiCurrencyAmount ceComputed = PRICER_TRADE.currencyExposure(OPTION_TRADE, RATES_PROVIDER, VOLS);
    PointSensitivities point = PRICER_TRADE.presentValueSensitivityRatesStickyStrike(OPTION_TRADE, RATES_PROVIDER, VOLS);
    MultiCurrencyAmount pv = PRICER_TRADE.presentValue(OPTION_TRADE, RATES_PROVIDER, VOLS);
    MultiCurrencyAmount ceExpected = RATES_PROVIDER.currencyExposure(point).plus(pv);
    assertThat(ceComputed.size()).isEqualTo(2);
    assertThat(ceComputed.getAmount(EUR).getAmount()).isCloseTo(ceExpected.getAmount(EUR).getAmount(), offset(TOL * NOTIONAL));
    assertThat(ceComputed.getAmount(USD).getAmount()).isCloseTo(ceExpected.getAmount(USD).getAmount(), offset(TOL * NOTIONAL));
  }