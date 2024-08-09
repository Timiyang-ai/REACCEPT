  @Test
  public void test_metadata() {
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
    DefaultCurveMetadata expected = DefaultCurveMetadata.builder()
        .curveName(CURVE_NAME)
        .xValueType(ValueType.YEAR_FRACTION)
        .yValueType(ValueType.ZERO_RATE)
        .dayCount(ACT_365F)
        .parameterMetadata(NODES.get(0).metadata(VAL_DATE, REF_DATA), NODES.get(1).metadata(VAL_DATE, REF_DATA))
        .build();
    assertThat(test.metadata(VAL_DATE, REF_DATA)).isEqualTo(expected);
  }