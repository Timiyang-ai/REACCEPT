public ServiceCall<TranslationResult> translate(final String text, final String modelId) {
    Validate.isTrue(modelId != null && !modelId.isEmpty(), "modelId cannot be null or empty");
    return createServiceCall(createCall(translateRequest(text, modelId, null, null)), ResponseUtil.getObjectConverter(TranslationResult.class));
  }