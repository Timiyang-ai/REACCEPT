public ServiceCall<Classification> classify(ClassifyOptions classifyOptions) {
    Validator.notNull(classifyOptions, "classifyOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(String.format("/v1/classifiers/%s/classify", classifyOptions
        .classifierId()));
    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty("text", classifyOptions.text());
    builder.bodyJson(contentJson);
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(Classification.class));
  }