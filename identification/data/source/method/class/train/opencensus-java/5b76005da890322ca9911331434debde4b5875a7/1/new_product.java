@Nullable
  @VisibleForTesting
  static Resource toResourceProto(@Nullable io.opencensus.resource.Resource resource) {
    if (resource == null || resource.getType() == null) {
      return null;
    } else {
      Resource.Builder resourceProtoBuilder = Resource.newBuilder();
      resourceProtoBuilder.setType(resource.getType());
      for (Entry<String, String> keyValuePairs : resource.getLabels().entrySet()) {
        resourceProtoBuilder.putLabels(keyValuePairs.getKey(), keyValuePairs.getValue());
      }
      return resourceProtoBuilder.build();
    }
  }