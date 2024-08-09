  @Test
  public void test_pvbpSensitivity() {
    ResolvedSwapLeg leg = ResolvedSwapLeg.builder()
        .type(FIXED)
        .payReceive(PAY)
        .paymentPeriods(FIXED_RATE_PAYMENT_PERIOD_PAY_USD, FIXED_RATE_PAYMENT_PERIOD_PAY_USD_2)
        .build();
    PointSensitivities point = PRICER_LEG.pvbpSensitivity(leg, RATES_USD).build();
    CurrencyParameterSensitivities pvbpsAd = RATES_USD.parameterSensitivity(point);
    CurrencyParameterSensitivities pvbpsFd = FINITE_DIFFERENCE_CALCULATOR.sensitivity(RATES_USD,
        (p) -> CurrencyAmount.of(USD, PRICER_LEG.pvbp(leg, p)));
    assertThat(pvbpsAd.equalWithTolerance(pvbpsFd, TOLERANCE_DELTA)).isTrue();
  }