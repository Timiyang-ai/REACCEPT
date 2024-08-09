  @Test
  public void test_presentValueSensitivity() {
    PointSensitivityBuilder pvPointCoupon = PRICER.presentValueSensitivityRates(COUPON_SELL, RATES_PROVIDER, VOLATILITIES);
    CurrencyParameterSensitivities computedCoupon = RATES_PROVIDER
        .parameterSensitivity(pvPointCoupon.build());
    CurrencyParameterSensitivities expectedCoupon = FD_CAL.sensitivity(
        RATES_PROVIDER, p -> PRICER.presentValue(COUPON_SELL, p, VOLATILITIES));
    assertThat(computedCoupon.equalWithTolerance(expectedCoupon, EPS * NOTIONAL * 50d)).isTrue();
    PointSensitivityBuilder pvCapPoint = PRICER.presentValueSensitivityRates(CAPLET_SELL, RATES_PROVIDER, VOLATILITIES);
    CurrencyParameterSensitivities computedCap = RATES_PROVIDER.parameterSensitivity(pvCapPoint.build());
    CurrencyParameterSensitivities expectedCap = FD_CAL.sensitivity(
        RATES_PROVIDER, p -> PRICER.presentValue(CAPLET_SELL, p, VOLATILITIES));
    assertThat(computedCap.equalWithTolerance(expectedCap, EPS * NOTIONAL * 50d)).isTrue();
    PointSensitivityBuilder pvFloorPoint = PRICER.presentValueSensitivityRates(FLOORLET_SELL, RATES_PROVIDER, VOLATILITIES);
    CurrencyParameterSensitivities computedFloor = RATES_PROVIDER.parameterSensitivity(pvFloorPoint.build());
    CurrencyParameterSensitivities expectedFloor = FD_CAL.sensitivity(
        RATES_PROVIDER, p -> PRICER.presentValue(FLOORLET_SELL, p, VOLATILITIES));
    assertThat(computedFloor.equalWithTolerance(expectedFloor, EPS * NOTIONAL * 10d)).isTrue();
  }