@SuppressWarnings("unchecked")
  public static CurveMetadata zeroRates(
      CurveName name,
      DayCount dayCount,
      List<? extends CurveParameterMetadata> parameterMetadata) {

    ArgChecker.notNull(name, "name");
    ArgChecker.notNull(dayCount, "dayCount");
    return DefaultCurveMetadata.builder()
        .curveName(name)
        .xValueType(ValueType.YEAR_FRACTION)
        .yValueType(ValueType.ZERO_RATE)
        .dayCount(dayCount)
        .parameterMetadata((List<CurveParameterMetadata>) parameterMetadata)
        .build();
  }