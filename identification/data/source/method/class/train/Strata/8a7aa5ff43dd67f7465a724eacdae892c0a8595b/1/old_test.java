  @Test
  public void test_builder1() {
    DefaultCurveMetadata test = DefaultCurveMetadata.builder()
        .curveName(CURVE_NAME.toString())
        .xValueType(ValueType.YEAR_FRACTION)
        .yValueType(ValueType.DISCOUNT_FACTOR)
        .dayCount(ACT_360)
        .jacobian(JACOBIAN_DATA)
        .addInfo(CurveInfoType.DAY_COUNT, null)
        .parameterMetadata(ImmutableList.of(ParameterMetadata.empty()))
        .build();
    assertThat(test.getCurveName()).isEqualTo(CURVE_NAME);
    assertThat(test.getXValueType()).isEqualTo(ValueType.YEAR_FRACTION);
    assertThat(test.getYValueType()).isEqualTo(ValueType.DISCOUNT_FACTOR);
    assertThat(test.findInfo(CurveInfoType.DAY_COUNT)).isEmpty();
    assertThat(test.getInfo(CurveInfoType.JACOBIAN)).isEqualTo(JACOBIAN_DATA);
    assertThat(test.findInfo(CurveInfoType.JACOBIAN)).isEqualTo(Optional.of(JACOBIAN_DATA));
    assertThat(test.findInfo(CurveInfoType.of("Rubbish"))).isEqualTo(Optional.empty());
    assertThat(test.getParameterMetadata().isPresent()).isTrue();
    assertThat(test.getParameterMetadata().get()).containsExactly(ParameterMetadata.empty());
  }