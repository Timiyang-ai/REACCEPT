public DocumentAuthors getAuthors(Map<String, Object> params) {
		return executeRequest(params, AlchemyAPI.authors, DocumentAuthors.class, "html", "url");
	}