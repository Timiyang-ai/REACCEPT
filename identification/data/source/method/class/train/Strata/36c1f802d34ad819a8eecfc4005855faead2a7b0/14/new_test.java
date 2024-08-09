  @Test
  public void test_presentValueSensitivityWithSpread() {
    PointSensitivityBuilder computed = PRICER.presentValueSensitivityWithSpread(
        PAYMENT_PERIOD, ISSUER_CURVE, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR);
    PointSensitivityBuilder expected = IssuerCurveZeroRateSensitivity.of(
        DSC_FACTORS.zeroRatePointSensitivityWithSpread(END_ADJUSTED, Z_SPREAD, PERIODIC, PERIOD_PER_YEAR)
            .multipliedBy(FIXED_RATE * NOTIONAL * YEAR_FRACTION), GROUP);
    assertThat(computed).isEqualTo(expected);
  }