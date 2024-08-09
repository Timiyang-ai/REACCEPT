public Classifiers getClassifiers() {
		HttpRequestBase request = Request.Get("/v1/classifiers").build();

		try {
			HttpResponse response = execute(request);
			return ResponseUtil.getObject(response, Classifiers.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}