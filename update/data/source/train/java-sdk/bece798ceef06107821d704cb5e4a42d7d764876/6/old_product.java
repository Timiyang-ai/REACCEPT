public Language getLanguage(Map<String, Object> params) {
		return executeRequest(params, AlchemyAPI.language, Language.class, "text", "html", "url");
	}