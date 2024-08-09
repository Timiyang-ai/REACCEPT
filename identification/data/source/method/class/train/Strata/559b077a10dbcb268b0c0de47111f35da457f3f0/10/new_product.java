@Override
  public DefaultSurfaceMetadata withParameterMetadata(List<? extends SurfaceParameterMetadata> parameterMetadata) {
    if (parameterMetadata == null) {
      return this.parameterMetadata != null ? toBuilder().clearParameterMetadata().build() : this;
    }
    return toBuilder().parameterMetadata(parameterMetadata).build();
  }