  @Test
  public void test_forecastValueSensitivity_beforeStart() {
    PointSensitivityBuilder pointInterp =
        PRICER.forecastValueSensitivity(PERIOD_INTERP, IRP_BEFORE_START);
    CurrencyParameterSensitivities computedInterp =
        IRP_BEFORE_START.parameterSensitivity(pointInterp.build());
    PointSensitivityBuilder pointMonthly =
        PRICER.forecastValueSensitivity(PERIOD_MONTHLY, IRP_BEFORE_START);
    CurrencyParameterSensitivities computedMonthly =
        IRP_BEFORE_START.parameterSensitivity(pointMonthly.build());
    CurrencyParameterSensitivities expectedInterp =
        FD_CAL.sensitivity(IRP_BEFORE_START, p -> CurrencyAmount.of(USD, PRICER.forecastValue(PERIOD_INTERP, p)));
    CurrencyParameterSensitivities expectedMonthly =
        FD_CAL.sensitivity(IRP_BEFORE_START, p -> CurrencyAmount.of(USD, PRICER.forecastValue(PERIOD_MONTHLY, p)));
    assertThat(computedInterp.equalWithTolerance(expectedInterp, NOTIONAL * FD_EPS)).isTrue();
    assertThat(computedMonthly.equalWithTolerance(expectedMonthly, NOTIONAL * FD_EPS)).isTrue();
  }