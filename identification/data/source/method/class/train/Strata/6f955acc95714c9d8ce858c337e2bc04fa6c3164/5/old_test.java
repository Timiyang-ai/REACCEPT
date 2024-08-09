  @Test
  public void prices_string() {
    CurveMetadata test = Curves.prices(NAME);
    CurveMetadata expected = DefaultCurveMetadata.builder()
        .curveName(CURVE_NAME)
        .xValueType(ValueType.MONTHS)
        .yValueType(ValueType.PRICE_INDEX)
        .build();
    assertThat(test).isEqualTo(expected);
  }