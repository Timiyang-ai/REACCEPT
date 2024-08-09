@Override
  public DefaultCurveMetadata withParameterMetadata(List<CurveParameterMetadata> parameterMetadata) {
    return toBuilder().parameterMetadata(parameterMetadata).build();
  }