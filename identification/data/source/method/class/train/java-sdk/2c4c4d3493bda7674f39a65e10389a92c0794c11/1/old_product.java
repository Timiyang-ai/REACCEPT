public TranslationResult translate(final Map<String, Object> params) {

    final String source = (String) params.get(SOURCE);
    final String target = (String) params.get(TARGET);
    final String modelId = (String) params.get(MODEL_ID);
    final String[] text;
    if (params.get(TEXT) != null) {
      if (params.get(TEXT) instanceof String)
        text = new String[] {(String) params.get(TEXT)};
      else
        text = (String[]) params.get(TEXT);
    } else {
      text = null;
    }

    if ((modelId == null || modelId.isEmpty())
        && (source == null || source.isEmpty() || target == null || target.isEmpty()))
      throw new IllegalArgumentException("model_id or source and target should be specified");

    if (text == null)
      throw new IllegalArgumentException("text cannot be null");

    final JsonObject contentJson = new JsonObject();

    // convert the text into a json array
    final JsonArray paragraphs = new JsonArray();
    for (final String paragraph : text) {
      paragraphs.add(new JsonPrimitive(paragraph));
    }
    contentJson.add(TEXT, paragraphs);

    final RequestBuilder requestBuilder =
        RequestBuilder.post("/v2/translate").withBodyJson(contentJson);

    if (source != null && !source.isEmpty())
      requestBuilder.withQuery(SOURCE, source);

    if (target != null && !target.isEmpty())
      requestBuilder.withQuery(TARGET, target);

    if (modelId != null && !modelId.isEmpty())
      requestBuilder.withQuery(MODEL_ID, modelId);

    return executeRequest(requestBuilder.build(), TranslationResult.class);
  }