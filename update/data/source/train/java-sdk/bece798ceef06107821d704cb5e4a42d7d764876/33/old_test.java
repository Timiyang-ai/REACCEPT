@Test
	public void testTranslate() {

		// Mocking the response
		Map<String, Object> response = new HashMap<String, Object>();
		List<Translation> translations = new ArrayList<Translation>();

		Translation t = new Translation().withTranslation("El equipo es increible IBM Watson");
		translations.add(t);

		response.put("word_count", 6);
		response.put("character_count", 20);
		response.put("translations", translations);

		String[] text1 = new String[] { text };

		JsonObject contentJson = new JsonObject();
		JsonArray paragraphs = new JsonArray();
		for (String paragraph : text1) {
			paragraphs.add(new JsonPrimitive(paragraph));
		}
		contentJson.add(LanguageTranslation.TEXT, paragraphs);
		mockServer.when(
				request().withMethod("POST").withPath(LANGUAGE_TRANSLATION_PATH)
						.withQueryStringParameter(new Parameter(LanguageTranslation.MODEL_ID, modelId))
						.withBody(contentJson.toString())

		)
		.respond(
				response().withHeaders(new Header(HttpHeaders.Names.CONTENT_TYPE, MediaType.APPLICATION_JSON))
						.withBody(GsonSingleton.getGson().toJson(response)));

		TranslationResult translationResult = service.translate(text, modelId);
		testTranslationResult(text, translationResult);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(LanguageTranslation.TEXT, new String[] { text });
		params.put(LanguageTranslation.MODEL_ID, modelId);

		translationResult = service.translate(params);
		testTranslationResult(text, translationResult);
		assertNotNull(service.toString());
	}