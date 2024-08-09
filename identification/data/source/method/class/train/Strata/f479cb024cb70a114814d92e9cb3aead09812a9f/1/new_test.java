  @Test
  public void forwardRates_string() {
    CurveMetadata test = Curves.forwardRates(NAME, ACT_360);
    CurveMetadata expected = DefaultCurveMetadata.builder()
        .curveName(CURVE_NAME)
        .xValueType(ValueType.YEAR_FRACTION)
        .yValueType(ValueType.FORWARD_RATE)
        .dayCount(ACT_360)
        .build();
    assertThat(test).isEqualTo(expected);
  }