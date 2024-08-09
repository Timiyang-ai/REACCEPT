public ServiceCall<TranslationModel> getModel(String modelId) {
    if (modelId == null || modelId.isEmpty())
      throw new IllegalArgumentException("modelId cannot be null or empty");

    return createServiceCall(createCall(RequestBuilder.get(String.format(PATH_MODEL, modelId)).build3()),
            ResponseUtil.getObjectConverter(TranslationModel.class));
  }