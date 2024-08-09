  @Test
  public void test_parameterSensitivity() {
    ZeroRatePeriodicDiscountFactors test = ZeroRatePeriodicDiscountFactors.of(GBP, DATE_VAL, CURVE);
    double sensiValue = 25d;
    ZeroRateSensitivity point = test.zeroRatePointSensitivity(DATE_AFTER);
    point = point.multipliedBy(sensiValue);
    CurrencyParameterSensitivities sensiObject = test.parameterSensitivity(point);
    assertThat(sensiObject.size()).isEqualTo(1);
    CurrencyParameterSensitivity sensi1 = sensiObject.getSensitivities().get(0);
    assertThat(sensi1.getCurrency()).isEqualTo(GBP);
  }