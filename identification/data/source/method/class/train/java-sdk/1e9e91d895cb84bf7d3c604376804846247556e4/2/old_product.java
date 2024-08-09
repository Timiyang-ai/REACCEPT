public ServiceCall<Value> createValue(CreateValueOptions createValueOptions) {
    Validator.notNull(createValueOptions, "createValueOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(String.format("/v1/workspaces/%s/entities/%s/values",
        createValueOptions.workspaceId(), createValueOptions.entity()));
    builder.query(VERSION, versionDate);
    final JsonObject contentJson = new JsonObject();
    if (createValueOptions.metadata() != null) {
      contentJson.add("metadata", GsonSingleton.getGson().toJsonTree(createValueOptions.metadata()));
    }
    if (createValueOptions.synonyms() != null) {
      contentJson.add("synonyms", GsonSingleton.getGson().toJsonTree(createValueOptions.synonyms()));
    }
    contentJson.addProperty("value", createValueOptions.value());
    builder.bodyJson(contentJson);
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(Value.class));
  }