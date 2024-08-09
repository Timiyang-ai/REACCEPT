  @Test
  public void test_curve() {
    InterpolatedNodalCurveDefinition test = InterpolatedNodalCurveDefinition.builder()
        .name(CURVE_NAME)
        .xValueType(ValueType.YEAR_FRACTION)
        .yValueType(ValueType.ZERO_RATE)
        .dayCount(ACT_365F)
        .nodes(NODES)
        .interpolator(CurveInterpolators.LINEAR)
        .extrapolatorLeft(CurveExtrapolators.FLAT)
        .extrapolatorRight(CurveExtrapolators.FLAT)
        .build();
    DefaultCurveMetadata metadata = DefaultCurveMetadata.builder()
        .curveName(CURVE_NAME)
        .xValueType(ValueType.YEAR_FRACTION)
        .yValueType(ValueType.ZERO_RATE)
        .dayCount(ACT_365F)
        .parameterMetadata(NODES.get(0).metadata(VAL_DATE, REF_DATA), NODES.get(1).metadata(VAL_DATE, REF_DATA))
        .build();
    InterpolatedNodalCurve expected = InterpolatedNodalCurve.builder()
        .metadata(metadata)
        .xValues(DoubleArray.of(ACT_365F.yearFraction(VAL_DATE, DATE1), ACT_365F.yearFraction(VAL_DATE, DATE2)))
        .yValues(DoubleArray.of(1d, 1.5d))
        .interpolator(CurveInterpolators.LINEAR)
        .extrapolatorLeft(CurveExtrapolators.FLAT)
        .extrapolatorRight(CurveExtrapolators.FLAT)
        .build();
    assertThat(test.curve(VAL_DATE, metadata, DoubleArray.of(1d, 1.5d))).isEqualTo(expected);
  }