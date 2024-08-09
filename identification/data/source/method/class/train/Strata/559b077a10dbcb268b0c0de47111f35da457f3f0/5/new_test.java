  @Test
  public void zeroRates_string() {
    CurveMetadata test = Curves.zeroRates(NAME, ACT_360);
    CurveMetadata expected = DefaultCurveMetadata.builder()
        .curveName(CURVE_NAME)
        .xValueType(ValueType.YEAR_FRACTION)
        .yValueType(ValueType.ZERO_RATE)
        .dayCount(ACT_360)
        .build();
    assertThat(test).isEqualTo(expected);
  }