private CurveMetadata metadata(
      LocalDate valuationDate,
      ReferenceData refData,
      Map<CurveInfoType<?>, Object> additionalInfo) {

    List<CurveParameterMetadata> nodeMetadata = nodes.stream()
        .map(node -> node.metadata(valuationDate, refData))
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