  @Test
  public void test_relative() {
    CurveParallelShifts test = CurveParallelShifts.relative(0.1d, 0.2d, 0.4d);

    Curve baseCurve = InterpolatedNodalCurve.of(
        Curves.zeroRates("curve", DayCounts.ACT_365F),
        DoubleArray.of(1, 2, 3),
        DoubleArray.of(5, 6, 7),
        CurveInterpolators.LOG_LINEAR);

    MarketDataBox<Curve> shiftedCurveBox = test.applyTo(MarketDataBox.ofSingleValue(baseCurve), REF_DATA);

    assertThat(shiftedCurveBox.getValue(0)).isEqualTo(ParallelShiftedCurve.relative(baseCurve, 0.1d));
    assertThat(shiftedCurveBox.getValue(1)).isEqualTo(ParallelShiftedCurve.relative(baseCurve, 0.2d));
    assertThat(shiftedCurveBox.getValue(2)).isEqualTo(ParallelShiftedCurve.relative(baseCurve, 0.4d));
  }