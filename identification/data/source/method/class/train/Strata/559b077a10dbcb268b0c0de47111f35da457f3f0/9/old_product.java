@SuppressWarnings("unchecked")
  public static CurveMetadata discountFactors(
      CurveName name,
      DayCount dayCount,
      List<? extends CurveParameterMetadata> parameterMetadata) {

    ArgChecker.notNull(name, "name");
    ArgChecker.notNull(dayCount, "dayCount");
    return DefaultCurveMetadata.builder()
        .curveName(name)
        .xValueType(ValueType.YEAR_FRACTION)
        .yValueType(ValueType.DISCOUNT_FACTOR)
        .dayCount(dayCount)
        .parameterMetadata((List<CurveParameterMetadata>) parameterMetadata)
        .build();
  }