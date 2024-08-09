public ServiceCall<AcousticModel> createAcousticModel(CreateAcousticModelOptions createAcousticModelOptions) {
    Validator.notNull(createAcousticModelOptions, "createAcousticModelOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post("/v1/acoustic_customizations");
    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty("name", createAcousticModelOptions.name());
    contentJson.addProperty("base_model_name", createAcousticModelOptions.baseModelName());
    if (createAcousticModelOptions.description() != null) {
      contentJson.addProperty("description", createAcousticModelOptions.description());
    }
    builder.bodyJson(contentJson);
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(AcousticModel.class));
  }