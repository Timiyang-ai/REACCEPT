public static CurveMetadata discountFactors(CurveName name, DayCount dayCount) {
    ArgChecker.notNull(name, "name");
    ArgChecker.notNull(dayCount, "dayCount");
    return DefaultCurveMetadata.builder()
        .curveName(name)
        .xValueType(ValueType.YEAR_FRACTION)
        .yValueType(ValueType.DISCOUNT_FACTOR)
        .dayCount(dayCount)
        .build();
  }