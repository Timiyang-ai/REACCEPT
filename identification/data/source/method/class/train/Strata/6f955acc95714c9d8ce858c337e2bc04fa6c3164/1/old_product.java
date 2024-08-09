@SuppressWarnings("unchecked")
  public static CurveMetadata prices(CurveName name, List<? extends CurveParameterMetadata> parameterMetadata) {
    ArgChecker.notNull(name, "name");
    return DefaultCurveMetadata.builder()
        .curveName(name)
        .xValueType(ValueType.MONTH_COUNT)
        .yValueType(ValueType.PRICE_INDEX)
        .parameterMetadata((List<CurveParameterMetadata>) parameterMetadata)
        .build();
  }