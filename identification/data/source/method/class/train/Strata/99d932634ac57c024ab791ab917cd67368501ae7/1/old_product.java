@Override
  public CurveMetadata withParameterMetadata(List<CurveParameterMetadata> parameterMetadata) {
    return toBuilder().parameterMetadata(parameterMetadata).build();
  }