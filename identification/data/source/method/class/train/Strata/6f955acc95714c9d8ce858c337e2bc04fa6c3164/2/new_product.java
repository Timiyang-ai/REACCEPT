public static CurveMetadata prices(CurveName name) {
    ArgChecker.notNull(name, "name");
    return DefaultCurveMetadata.builder()
        .curveName(name)
        .xValueType(ValueType.MONTHS)
        .yValueType(ValueType.PRICE_INDEX)
        .build();
  }