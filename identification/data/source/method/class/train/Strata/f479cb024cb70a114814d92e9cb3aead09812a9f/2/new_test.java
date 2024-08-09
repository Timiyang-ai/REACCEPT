  @Test
  public void sabrParameterByExpiry_string() {
    CurveMetadata test = Curves.sabrParameterByExpiry(NAME, ACT_365F, ValueType.SABR_ALPHA);
    CurveMetadata expected = DefaultCurveMetadata.builder()
        .curveName(CURVE_NAME)
        .xValueType(ValueType.YEAR_FRACTION)
        .yValueType(ValueType.SABR_ALPHA)
        .dayCount(ACT_365F)
        .build();
    assertThat(test).isEqualTo(expected);
  }