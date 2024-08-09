public ServiceCall<TranslationModel> getModel(String modelId) {
    Validator.isTrue(modelId != null && !modelId.isEmpty(), "modelId cannot be null or empty");
    Request request = RequestBuilder.get(String.format(PATH_MODEL, modelId)).build();
    return createServiceCall(request, ResponseConverterUtils.getObject(TranslationModel.class));
  }