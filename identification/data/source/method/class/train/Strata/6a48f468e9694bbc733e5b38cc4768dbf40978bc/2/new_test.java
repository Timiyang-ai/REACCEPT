  @Test
  public void test_currencyExposure() {
    MultiCurrencyAmount computedPricer = PRICER.currencyExposure(CALL_OTM, RATES_PROVIDER, VOLS);
    CurrencyAmount pv = PRICER.presentValue(CALL_OTM, RATES_PROVIDER, VOLS);
    PointSensitivities point = PRICER.presentValueSensitivityRatesStickyStrike(CALL_OTM, RATES_PROVIDER, VOLS);
    MultiCurrencyAmount computedPoint = RATES_PROVIDER.currencyExposure(point).plus(pv);
    assertThat(computedPricer.getAmount(EUR).getAmount()).isCloseTo(computedPoint.getAmount(EUR).getAmount(), offset(NOTIONAL * TOL));
    assertThat(computedPricer.getAmount(USD).getAmount()).isCloseTo(computedPoint.getAmount(USD).getAmount(), offset(NOTIONAL * TOL));
  }