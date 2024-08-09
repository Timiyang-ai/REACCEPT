private CurveMetadata metadata(LocalDate valuationDate, CurveCalibrationInfo calibrationInfo) {
    List<CurveParameterMetadata> nodeMetadata = nodes.stream()
        .map(node -> node.metadata(valuationDate))
        .collect(toImmutableList());
    return DefaultCurveMetadata.builder()
        .curveName(name)
        .xValueType(xValueType)
        .yValueType(yValueType)
        .dayCount(dayCount)
        .calibrationInfo(calibrationInfo)
        .parameterMetadata(nodeMetadata)
        .build();
  }