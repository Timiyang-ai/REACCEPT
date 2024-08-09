public ServiceCall<LanguageModel> createLanguageModel(CreateLanguageModelOptions createLanguageModelOptions) {
    Validator.notNull(createLanguageModelOptions, "createLanguageModelOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post("/v1/customizations");
    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty("name", createLanguageModelOptions.name());
    contentJson.addProperty("base_model_name", createLanguageModelOptions.baseModelName());
    if (createLanguageModelOptions.dialect() != null) {
      contentJson.addProperty("dialect", createLanguageModelOptions.dialect());
    }
    if (createLanguageModelOptions.description() != null) {
      contentJson.addProperty("description", createLanguageModelOptions.description());
    }
    builder.bodyJson(contentJson);
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(LanguageModel.class));
  }