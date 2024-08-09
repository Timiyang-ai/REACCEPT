  @Test
  public void discountFactors_string() {
    CurveMetadata test = Curves.discountFactors(NAME, ACT_360);
    CurveMetadata expected = DefaultCurveMetadata.builder()
        .curveName(CURVE_NAME)
        .xValueType(ValueType.YEAR_FRACTION)
        .yValueType(ValueType.DISCOUNT_FACTOR)
        .dayCount(ACT_360)
        .build();
    assertThat(test).isEqualTo(expected);
  }