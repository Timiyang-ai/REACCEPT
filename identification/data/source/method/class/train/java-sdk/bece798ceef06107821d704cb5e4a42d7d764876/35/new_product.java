public TranslationModel getModel(String modelId) {
    if (modelId == null || modelId.isEmpty())
      throw new IllegalArgumentException("model_id cannot be null or empty");

    final Request request = RequestBuilder.get("/v2/models/" + modelId).build();
    return executeRequest(request, TranslationModel.class);
  }