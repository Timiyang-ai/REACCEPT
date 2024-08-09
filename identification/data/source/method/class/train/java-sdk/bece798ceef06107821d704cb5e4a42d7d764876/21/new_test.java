@Test
  public void testTranslate() {

    // Mocking the response
    final Map<String, Object> response = new HashMap<String, Object>();
    final List<Translation> translations = new ArrayList<Translation>();

    final Translation t = new Translation().withTranslation("El equipo es increible IBM Watson");
    translations.add(t);

    response.put("word_count", 6);
    response.put("character_count", 20);
    response.put("translations", translations);

    final String[] text1 = new String[] {text};

    final JsonObject contentJson = new JsonObject();
    final JsonArray paragraphs = new JsonArray();
    for (final String paragraph : text1) {
      paragraphs.add(new JsonPrimitive(paragraph));
    }
    contentJson.add(LanguageTranslation.TEXT, paragraphs);
    mockServer.when(
        request().withMethod("POST").withPath(LANGUAGE_TRANSLATION_PATH)
            .withQueryStringParameter(new Parameter(LanguageTranslation.MODEL_ID, modelId))
            .withBody(contentJson.toString())

    ).respond(
        response().withHeaders(
            new Header(HttpHeaders.Names.CONTENT_TYPE, HttpMediaType.APPLICATION_JSON)).withBody(
            GsonSingleton.getGson().toJson(response)));

    TranslationResult translationResult = service.translate(text, modelId);
    testTranslationResult(text, translationResult);

    final Map<String, Object> params = new HashMap<String, Object>();
    params.put(LanguageTranslation.TEXT, new String[] {text});
    params.put(LanguageTranslation.MODEL_ID, modelId);

    translationResult = service.translate(params);
    testTranslationResult(text, translationResult);
    assertNotNull(service.toString());
  }