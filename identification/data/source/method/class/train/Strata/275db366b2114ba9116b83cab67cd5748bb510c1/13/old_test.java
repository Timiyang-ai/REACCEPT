  @Test
  public void test_currencyExposure() {
    MultiCurrencyAmount computedRec = PRICER.currencyExposure(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS);
    MultiCurrencyAmount computedPay = PRICER.currencyExposure(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS);
    PointSensitivityBuilder pointRec =
        PRICER.presentValueSensitivityRatesStickyModel(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS);
    MultiCurrencyAmount expectedRec = RATE_PROVIDER.currencyExposure(pointRec.build())
        .plus(PRICER.presentValue(SWAPTION_REC_LONG, RATE_PROVIDER, VOLS));
    assertThat(computedRec.size()).isEqualTo(1);
    assertThat(computedRec.getAmount(EUR).getAmount()).isCloseTo(expectedRec.getAmount(EUR).getAmount(), offset(NOTIONAL * TOL));
    PointSensitivityBuilder pointPay =
        PRICER.presentValueSensitivityRatesStickyModel(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS);
    MultiCurrencyAmount expectedPay = RATE_PROVIDER.currencyExposure(pointPay.build())
        .plus(PRICER.presentValue(SWAPTION_PAY_SHORT, RATE_PROVIDER, VOLS));
    assertThat(computedPay.size()).isEqualTo(1);
    assertThat(computedPay.getAmount(EUR).getAmount()).isCloseTo(expectedPay.getAmount(EUR).getAmount(), offset(NOTIONAL * TOL));
  }