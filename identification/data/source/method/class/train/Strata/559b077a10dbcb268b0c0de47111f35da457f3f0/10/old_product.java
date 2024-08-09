@Override
  public DefaultSurfaceMetadata withParameterMetadata(List<SurfaceParameterMetadata> parameterMetadata) {
    return toBuilder().parameterMetadata(parameterMetadata).build();
  }