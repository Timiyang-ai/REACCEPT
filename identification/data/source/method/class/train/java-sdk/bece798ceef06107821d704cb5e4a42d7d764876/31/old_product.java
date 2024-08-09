public List<IdentifiedLanguage> identify(final String text) {
		HttpRequestBase request = Request.Post("/v2/identify").withContent(text, MediaType.TEXT_PLAIN)
				.withHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).build();

		try {
			HttpResponse response = execute(request);
			JsonObject jsonObject = ResponseUtil.getJsonObject(response);
			List<IdentifiedLanguage> identifiedLanguages = GsonSingleton.getGson().fromJson(jsonObject.get(LANGUAGES),
					translationModelListType);
			return identifiedLanguages;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}