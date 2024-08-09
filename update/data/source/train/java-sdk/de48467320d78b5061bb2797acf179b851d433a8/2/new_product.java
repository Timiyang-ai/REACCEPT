public List<SynonymResult> getSynonyms(SynonymOptions options) {
    if (options == null)
      throw new IllegalArgumentException("options cannot be null");

    if (options.getWords() == null || options.getWords().isEmpty())
      throw new IllegalArgumentException("options.words cannot be null or empty");

    final Request request =
        RequestBuilder
            .post(PATH_SYNONYM)
            .withBodyContent(GsonSingleton.getGsonWithoutPrettyPrinting().toJson(options),
                HttpMediaType.APPLICATION_JSON).build();

    final Response response = execute(request);
    final String synonymResultJson = ResponseUtil.getString(response);
    final List<SynonymResult> synonyms =
        GsonSingleton.getGsonWithoutPrettyPrinting().fromJson(synonymResultJson, synonymListType);
    return synonyms;
  }