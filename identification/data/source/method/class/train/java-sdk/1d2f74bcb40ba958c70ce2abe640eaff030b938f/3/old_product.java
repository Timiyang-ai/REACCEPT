public ServiceCall<TranslationModel> getModel(String modelId) {
    if (modelId == null || modelId.isEmpty())
      throw new IllegalArgumentException("modelId cannot be null or empty");
    Request request = RequestBuilder.get(String.format(PATH_MODEL, modelId)).build();
    return createServiceCall(request, ResponseConverterUtils.getObject(TranslationModel.class));
  }