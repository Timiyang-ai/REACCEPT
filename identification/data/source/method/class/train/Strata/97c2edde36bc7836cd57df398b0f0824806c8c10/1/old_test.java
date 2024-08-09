  @Test
  public void test_presentValueSensitivityWithZSpread_beforeStart() {
    PointSensitivityBuilder pointInterp = PRICER.presentValueSensitivityWithZSpread(
        PERIOD_INTERP, IRP_BEFORE_START, ICDF_BEFORE_START, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR);
    CurrencyParameterSensitivities computedInterp1 =
        LEDP_BEFORE_START.parameterSensitivity(pointInterp.build());
    CurrencyParameterSensitivities computedInterp2 =
        IRP_BEFORE_START.parameterSensitivity(pointInterp.build());
    PointSensitivityBuilder pointMonthly = PRICER.presentValueSensitivityWithZSpread(
        PERIOD_MONTHLY, IRP_BEFORE_START, ICDF_BEFORE_START, Z_SPREAD, CONTINUOUS, 0);
    CurrencyParameterSensitivities computedMonthly1 =
        LEDP_BEFORE_START.parameterSensitivity(pointMonthly.build());
    CurrencyParameterSensitivities computedMonthly2 =
        IRP_BEFORE_START.parameterSensitivity(pointMonthly.build());
    CurrencyParameterSensitivities expectedInterp = fdSensitivityWithZSpread(
        PERIOD_INTERP, IRP_BEFORE_START, LEDP_BEFORE_START, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR);
    CurrencyParameterSensitivities expectedMonthly =
        fdSensitivityWithZSpread(PERIOD_MONTHLY, IRP_BEFORE_START, LEDP_BEFORE_START, Z_SPREAD, CONTINUOUS, 0);
    assertThat(computedInterp1.combinedWith(computedInterp2).equalWithTolerance(expectedInterp, NOTIONAL * FD_EPS)).isTrue();
    assertThat(computedMonthly1.combinedWith(computedMonthly2).equalWithTolerance(expectedMonthly, NOTIONAL * FD_EPS)).isTrue();
  }