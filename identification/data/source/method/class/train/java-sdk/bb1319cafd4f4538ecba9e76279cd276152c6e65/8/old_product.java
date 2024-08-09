public TranslationModel getModel(String modelId) {
    if (modelId == null || modelId.isEmpty())
      throw new IllegalArgumentException("modelId cannot be null or empty");

    final Request request = RequestBuilder.get(String.format(PATH_MODEL, modelId)).build();
    return executeRequest(request, TranslationModel.class);
  }