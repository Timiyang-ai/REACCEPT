@Override
  public CurveMetadata metadata(LocalDate valuationDate, ReferenceData refData) {
    List<ParameterMetadata> nodeMetadata = nodes.stream()
        .map(node -> node.metadata(valuationDate, refData))
        .collect(toImmutableList());
    return DefaultCurveMetadata.builder()
        .curveName(name)
        .xValueType(xValueType)
        .yValueType(yValueType)
        .dayCount(dayCount)
        .parameterMetadata(nodeMetadata)
        .build();
  }