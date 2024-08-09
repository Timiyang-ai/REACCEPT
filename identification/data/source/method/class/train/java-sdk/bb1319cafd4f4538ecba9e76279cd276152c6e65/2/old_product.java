public TranslationResult translate(final String text, final String modelId) {
    Validate.isTrue(modelId != null && !modelId.isEmpty(), "modelId cannot be null or empty");
    return translateRequest(text, modelId, null, null);
  }