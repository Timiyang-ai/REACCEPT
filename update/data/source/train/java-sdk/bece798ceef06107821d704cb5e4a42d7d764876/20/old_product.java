public List<SynonymResult> getSynonyms(Map<String, Object> params) {
		String[] words = (String[]) params.get(WORDS);
		String[] traits = (String[]) params.get(TRAITS);
		String[] contexts = (String[]) params.get(CONTEXT);

		if (words == null || words.length == 0)
			throw new IllegalArgumentException("words can not be null or empty");

		JsonObject contentJson = new JsonObject();

		// words
		JsonArray wordsJson = new JsonArray();
		for (String word : words) {
			wordsJson.add(new JsonPrimitive(word));
		}
		contentJson.add(WORDS, wordsJson);

		// traits
		if (traits != null && traits.length > 0) {
			JsonArray traisJson = new JsonArray();
			for (String trait : traits) {
				traisJson.add(new JsonPrimitive(trait));
			}
			contentJson.add(TRAITS, traisJson);
		}

		// context
		if (contexts != null && contexts.length > 0) {
			JsonArray contextsJson = new JsonArray();
			for (String context : contexts) {
				contextsJson.add(new JsonPrimitive(context));
			}
			contentJson.add(CONTEXT, contextsJson);
		}

		if (params.containsKey(LIMIT))
			contentJson.addProperty(LIMIT, (Integer) params.get(LIMIT));

		if (params.containsKey(HOPS))
			contentJson.addProperty(HOPS, (Integer) params.get(HOPS));

		HttpRequestBase request = Request.Post("/v1/synonym").withContent(contentJson).build();

		HttpResponse response = execute(request);
		try {
			String synonymResultJson = ResponseUtil.getString(response);
			List<SynonymResult> synonyms = GsonSingleton.getGson().fromJson(synonymResultJson, synonymListType);
			return synonyms;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}