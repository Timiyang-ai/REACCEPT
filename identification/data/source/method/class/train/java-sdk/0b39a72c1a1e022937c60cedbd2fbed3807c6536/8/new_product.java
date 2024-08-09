public Annotations annotateText(final Graph graph, final String text) {
		IDValidator.getGraphId(graph, getAccountId());
		Validate.notNull(text, "text can't be null");

		HttpRequestBase request = Request.Post(API_VERSION + graph.getId() + ANNOTATE_TEXT_PATH)
				.withContent(text, MediaType.TEXT_PLAIN).withHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.build();

		try {
			HttpResponse response = execute(request);
			return ResponseUtil.getObject(response, Annotations.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}