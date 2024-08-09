public ServiceCall<TranslationResult> translate(final String text, final String modelId) {
    return translate(Collections.singletonList(text), modelId);
  }