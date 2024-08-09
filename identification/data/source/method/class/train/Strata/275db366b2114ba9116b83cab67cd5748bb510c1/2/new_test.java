  @Test
  public void test_currencyExposure() {
    MultiCurrencyAmount computedRec = PRICER_SWAPTION.currencyExposure(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS);
    MultiCurrencyAmount computedPay = PRICER_SWAPTION.currencyExposure(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS);
    PointSensitivityBuilder pointRec =
        PRICER_SWAPTION.presentValueSensitivityRatesStickyStrike(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS);
    MultiCurrencyAmount expectedRec = RATE_PROVIDER.currencyExposure(pointRec.build())
        .plus(PRICER_SWAPTION.presentValue(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS));
    assertThat(computedRec.size()).isEqualTo(1);
    assertThat(computedRec.getAmount(USD).getAmount()).isCloseTo(expectedRec.getAmount(USD).getAmount(), offset(NOTIONAL * TOL));
    PointSensitivityBuilder pointPay =
        PRICER_SWAPTION.presentValueSensitivityRatesStickyStrike(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS);
    MultiCurrencyAmount expectedPay = RATE_PROVIDER.currencyExposure(pointPay.build())
        .plus(PRICER_SWAPTION.presentValue(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS));
    assertThat(computedPay.size()).isEqualTo(1);
    assertThat(computedPay.getAmount(USD).getAmount()).isCloseTo(expectedPay.getAmount(USD).getAmount(), offset(NOTIONAL * TOL));
  }