public ServiceCall<LanguageModel> createLanguageModel(CreateLanguageModelOptions createLanguageModelOptions) {
    Validator.notNull(createLanguageModelOptions, "createLanguageModelOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post("/v1/customizations");
    builder.header("Content-Type", createLanguageModelOptions.contentType());
    if (createLanguageModelOptions.contentType().equalsIgnoreCase(
        CreateLanguageModelOptions.ContentType.APPLICATION_JSON)) {
      builder.bodyJson(GsonSingleton.getGson().toJsonTree(createLanguageModelOptions.createLanguageModel())
          .getAsJsonObject());
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(LanguageModel.class));
  }