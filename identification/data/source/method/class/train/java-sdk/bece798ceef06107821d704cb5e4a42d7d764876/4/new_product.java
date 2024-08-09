public List<TranslationModel> getModels(final Boolean showDefault, final String source,
      final String target) {
    final RequestBuilder requestBuilder = RequestBuilder.get("/v2/models");

    if (source != null && !source.isEmpty())
      requestBuilder.withQuery(SOURCE, source);

    if (target != null && !target.isEmpty())
      requestBuilder.withQuery(TARGET, source);

    if (showDefault != null)
      requestBuilder.withQuery(DEFAULT, showDefault.booleanValue());

    final Response response = execute(requestBuilder.build());
    final JsonObject jsonObject = ResponseUtil.getJsonObject(response);
    final List<TranslationModel> models =
        GsonSingleton.getGson().fromJson(jsonObject.get("models"), modelListType);
    return models;
  }