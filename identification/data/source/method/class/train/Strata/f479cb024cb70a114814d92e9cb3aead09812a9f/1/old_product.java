@SuppressWarnings("unchecked")
  public static CurveMetadata forwardRates(
      CurveName name,
      DayCount dayCount,
      List<? extends ParameterMetadata> parameterMetadata) {

    ArgChecker.notNull(name, "name");
    ArgChecker.notNull(dayCount, "dayCount");
    return DefaultCurveMetadata.builder()
        .curveName(name)
        .xValueType(ValueType.YEAR_FRACTION)
        .yValueType(ValueType.FORWARD_RATE)
        .dayCount(dayCount)
        .parameterMetadata((List<ParameterMetadata>) parameterMetadata)
        .build();
  }