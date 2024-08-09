  @Test
  public void test_absolute() {
    CurveParallelShifts test = CurveParallelShifts.absolute(1d, 2d, 4d);

    Curve baseCurve = InterpolatedNodalCurve.of(
        Curves.zeroRates("curve", DayCounts.ACT_365F),
        DoubleArray.of(1, 2, 3),
        DoubleArray.of(5, 6, 7),
        CurveInterpolators.LOG_LINEAR);

    MarketDataBox<Curve> shiftedCurveBox = test.applyTo(MarketDataBox.ofSingleValue(baseCurve), REF_DATA);

    assertThat(shiftedCurveBox.getValue(0)).isEqualTo(ParallelShiftedCurve.absolute(baseCurve, 1d));
    assertThat(shiftedCurveBox.getValue(1)).isEqualTo(ParallelShiftedCurve.absolute(baseCurve, 2d));
    assertThat(shiftedCurveBox.getValue(2)).isEqualTo(ParallelShiftedCurve.absolute(baseCurve, 4d));
  }