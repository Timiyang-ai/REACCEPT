private CurveMetadata metadata(LocalDate valuationDate, Map<CurveInfoType<?>, Object> additionalInfo) {
    List<CurveParameterMetadata> nodeMetadata = nodes.stream()
        .map(node -> node.metadata(valuationDate))
        .collect(toImmutableList());
    return DefaultCurveMetadata.builder()
        .curveName(name)
        .xValueType(xValueType)
        .yValueType(yValueType)
        .dayCount(dayCount)
        .addInfo(additionalInfo)
        .parameterMetadata(nodeMetadata)
        .build();
  }