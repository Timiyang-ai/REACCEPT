public List<TranslationModel> getModels(final Boolean showDefault, final String source, final String target) {
		Request request = Request.Get("/v2/models");

		if (source != null && !source.isEmpty())
			request.withQuery(SOURCE, source);

		if (target != null && !target.isEmpty())
			request.withQuery(TARGET, source);

		if (showDefault != null)
			request.withQuery(DEFAULT, showDefault.booleanValue());

		HttpRequestBase requestBase = request.build();
		try {
			HttpResponse response = execute(requestBase);
			JsonObject jsonObject = ResponseUtil.getJsonObject(response);
			List<TranslationModel> models = GsonSingleton.getGson().fromJson(jsonObject.get("models"), modelListType);
			return models;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}