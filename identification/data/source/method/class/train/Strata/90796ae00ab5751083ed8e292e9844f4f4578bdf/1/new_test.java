  @Test
  public void test_currencyExposure() {
    MultiCurrencyAmount computedRec = PRICER.currencyExposure(SWAPTION_REC_LONG, RATE_PROVIDER, HW_PROVIDER);
    MultiCurrencyAmount computedPay = PRICER.currencyExposure(SWAPTION_PAY_SHORT, RATE_PROVIDER, HW_PROVIDER);
    PointSensitivityBuilder pointRec =
        PRICER.presentValueSensitivityRates(SWAPTION_REC_LONG, RATE_PROVIDER, HW_PROVIDER);
    MultiCurrencyAmount expectedRec = RATE_PROVIDER.currencyExposure(pointRec.build())
        .plus(PRICER.presentValue(SWAPTION_REC_LONG, RATE_PROVIDER, HW_PROVIDER));
    assertThat(computedRec.size()).isEqualTo(1);
    assertThat(computedRec.getAmount(EUR).getAmount()).isCloseTo(expectedRec.getAmount(EUR).getAmount(), offset(NOTIONAL * TOL));
    PointSensitivityBuilder pointPay =
        PRICER.presentValueSensitivityRates(SWAPTION_PAY_SHORT, RATE_PROVIDER, HW_PROVIDER);
    MultiCurrencyAmount expectedPay = RATE_PROVIDER.currencyExposure(pointPay.build())
        .plus(PRICER.presentValue(SWAPTION_PAY_SHORT, RATE_PROVIDER, HW_PROVIDER));
    assertThat(computedPay.size()).isEqualTo(1);
    assertThat(computedPay.getAmount(EUR).getAmount()).isCloseTo(expectedPay.getAmount(EUR).getAmount(), offset(NOTIONAL * TOL));
  }