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
    contentJson.add("text", paragraphs);
    contentJson.addProperty("model_id", modelId);
    mockServer.when(request().withMethod("POST").withPath(LANGUAGE_TRANSLATION_PATH)

    .withBody(contentJson.toString())

    ).respond(
        response().withHeaders(
            new Header(HttpHeaders.Names.CONTENT_TYPE, HttpMediaType.APPLICATION_JSON)).withBody(
            GsonSingleton.getGsonWithoutPrettyPrinting().toJson(response)));

    TranslationResult translationResult = service.translate3(text, modelId).execute();
    testTranslationResult(text, translationResult);

    translationResult = service.translate3(text, modelId).execute();
    testTranslationResult(text, translationResult);
    assertNotNull(service.toString());
  }