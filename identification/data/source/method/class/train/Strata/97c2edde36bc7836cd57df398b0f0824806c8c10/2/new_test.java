  @Test
  public void test_presentValueSensitivity_beforeStart() {
    PointSensitivityBuilder pointInterp = 
        PRICER.presentValueSensitivity(PERIOD_INTERP, IRP_BEFORE_START, ICDF_BEFORE_START);
    CurrencyParameterSensitivities computedInterp1 =
        LEDP_BEFORE_START.parameterSensitivity(pointInterp.build());
    CurrencyParameterSensitivities computedInterp2 =
        IRP_BEFORE_START.parameterSensitivity(pointInterp.build());
    PointSensitivityBuilder pointMonthly =
        PRICER.presentValueSensitivity(PERIOD_MONTHLY, IRP_BEFORE_START, ICDF_BEFORE_START);
    CurrencyParameterSensitivities computedMonthly1 =
        LEDP_BEFORE_START.parameterSensitivity(pointMonthly.build());
    CurrencyParameterSensitivities computedMonthly2 =
        IRP_BEFORE_START.parameterSensitivity(pointMonthly.build());
    CurrencyParameterSensitivities expectedInterp =
        fdSensitivity(PERIOD_INTERP, IRP_BEFORE_START, LEDP_BEFORE_START);
    CurrencyParameterSensitivities expectedMonthly =
        fdSensitivity(PERIOD_MONTHLY, IRP_BEFORE_START, LEDP_BEFORE_START);
    assertThat(computedInterp1.combinedWith(computedInterp2).equalWithTolerance(expectedInterp, NOTIONAL * FD_EPS)).isTrue();
    assertThat(computedMonthly1.combinedWith(computedMonthly2).equalWithTolerance(expectedMonthly, NOTIONAL * FD_EPS)).isTrue();
  }