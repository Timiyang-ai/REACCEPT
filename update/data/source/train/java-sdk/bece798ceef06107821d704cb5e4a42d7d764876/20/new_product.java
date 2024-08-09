public List<SynonymResult> getSynonyms(Map<String, Object> params) {
    final String[] words = (String[]) params.get(WORDS);
    final String[] traits = (String[]) params.get(TRAITS);
    final String[] contexts = (String[]) params.get(CONTEXT);

    if (words == null || words.length == 0)
      throw new IllegalArgumentException("words cannot be null or empty");

    final JsonObject contentJson = new JsonObject();

    // words
    final JsonArray wordsJson = new JsonArray();
    for (final String word : words) {
      wordsJson.add(new JsonPrimitive(word));
    }
    contentJson.add(WORDS, wordsJson);

    // traits
    if (traits != null && traits.length > 0) {
      final JsonArray traisJson = new JsonArray();
      for (final String trait : traits) {
        traisJson.add(new JsonPrimitive(trait));
      }
      contentJson.add(TRAITS, traisJson);
    }

    // context
    if (contexts != null && contexts.length > 0) {
      final JsonArray contextsJson = new JsonArray();
      for (final String context : contexts) {
        contextsJson.add(new JsonPrimitive(context));
      }
      contentJson.add(CONTEXT, contextsJson);
    }

    if (params.containsKey(LIMIT))
      contentJson.addProperty(LIMIT, (Integer) params.get(LIMIT));

    if (params.containsKey(HOPS))
      contentJson.addProperty(HOPS, (Integer) params.get(HOPS));

    final Request request = RequestBuilder.post(PATH_SYNONYM).withBodyJson(contentJson).build();

    final Response response = execute(request);
    final String synonymResultJson = ResponseUtil.getString(response);
    final List<SynonymResult> synonyms =
        GsonSingleton.getGson().fromJson(synonymResultJson, synonymListType);
    return synonyms;
  }