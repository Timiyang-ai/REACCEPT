public Annotations annotateText(final Graph graph, final String text) {
		String graphId = IDHelper.getGraphId(graph, getAccountId());
		Validate.notEmpty(text, "text cannot be empty");

		HttpRequestBase request = Request.Post(API_VERSION + graphId + ANNOTATE_TEXT_PATH)
				.withContent(text, MediaType.TEXT_PLAIN).withHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
				.build();

		try {
			HttpResponse response = execute(request);
			return ResponseUtil.getObject(response, Annotations.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}