@SuppressWarnings("unchecked")
  public static CurveMetadata prices(CurveName name, List<? extends ParameterMetadata> parameterMetadata) {
    ArgChecker.notNull(name, "name");
    return DefaultCurveMetadata.builder()
        .curveName(name)
        .xValueType(ValueType.MONTHS)
        .yValueType(ValueType.PRICE_INDEX)
        .parameterMetadata((List<ParameterMetadata>) parameterMetadata)
        .build();
  }