public ServiceCall<AcousticModel> createAcousticModel(CreateAcousticModelOptions createAcousticModelOptions) {
    Validator.notNull(createAcousticModelOptions, "createAcousticModelOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post("/v1/acoustic_customizations");
    builder.header("Content-Type", createAcousticModelOptions.contentType());
    if (createAcousticModelOptions.contentType().equalsIgnoreCase(
        CreateAcousticModelOptions.ContentType.APPLICATION_JSON)) {
      builder.bodyJson(GsonSingleton.getGson().toJsonTree(createAcousticModelOptions.createAcousticModel())
          .getAsJsonObject());
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(AcousticModel.class));
  }