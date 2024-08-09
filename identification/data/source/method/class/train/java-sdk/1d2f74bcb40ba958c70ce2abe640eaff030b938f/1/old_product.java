public ServiceCall<List<TranslationModel>> getModels(final Boolean showDefault, final String source,
      final String target) {
    final RequestBuilder requestBuilder = RequestBuilder.get(PATH_MODELS);

    if (source != null && !source.isEmpty())
      requestBuilder.withQuery(SOURCE, source);

    if (target != null && !target.isEmpty())
      requestBuilder.withQuery(TARGET, source);

    if (showDefault != null)
      requestBuilder.withQuery(DEFAULT, showDefault);

    ResponseConverter<List<TranslationModel>> converter =
        ResponseConverterUtils.getGenericObject(TYPE_LIST_TRANSLATION_MODEL, "models");

    return createServiceCall(requestBuilder.build(), converter);
  }