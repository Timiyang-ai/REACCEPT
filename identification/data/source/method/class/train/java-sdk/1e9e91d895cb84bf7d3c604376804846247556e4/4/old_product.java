public ServiceCall<Value> updateValue(UpdateValueOptions updateValueOptions) {
    Validator.notNull(updateValueOptions, "updateValueOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(String.format("/v1/workspaces/%s/entities/%s/values/%s",
        updateValueOptions.workspaceId(), updateValueOptions.entity(), updateValueOptions.value()));
    builder.query(VERSION, versionDate);
    final JsonObject contentJson = new JsonObject();
    if (updateValueOptions.newSynonyms() != null) {
      contentJson.add("synonyms", GsonSingleton.getGson().toJsonTree(updateValueOptions.newSynonyms()));
    }
    if (updateValueOptions.newMetadata() != null) {
      contentJson.add("metadata", GsonSingleton.getGson().toJsonTree(updateValueOptions.newMetadata()));
    }
    if (updateValueOptions.newValue() != null) {
      contentJson.addProperty("value", updateValueOptions.newValue());
    }
    builder.bodyJson(contentJson);
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(Value.class));
  }