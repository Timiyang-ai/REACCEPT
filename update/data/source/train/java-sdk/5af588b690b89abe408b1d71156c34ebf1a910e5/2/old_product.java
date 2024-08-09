public ServiceCall<SpeechModel> getModel(final String modelName) {
    Validator.notNull(modelName, "name cannot be null");

    Request request = RequestBuilder.get(String.format(PATH_MODEL, modelName)).build();
    return createServiceCall(request, ResponseConverterUtils.getObject(SpeechModel.class));
  }