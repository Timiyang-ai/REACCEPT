@Nullable
  @VisibleForTesting
  static Resource toResourceProto(@Nullable MonitoredResource resource) {
    if (resource == null) {
      return null;
    } else {
      Resource.Builder resourceProtoBuilder = Resource.newBuilder();
      resourceProtoBuilder.setType(resource.getResourceType().name());
      putResourceLabels(resource, resourceProtoBuilder);
      return resourceProtoBuilder.build();
    }
  }