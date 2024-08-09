public ServiceCall<SpeechModel> getModel(GetModelOptions getModelOptions) {
    Validator.notNull(getModelOptions, "getModelOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v1/models/%s", getModelOptions.modelId()));
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(SpeechModel.class));
  }