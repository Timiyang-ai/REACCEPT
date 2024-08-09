public Classifier getClassifier(String classifierId) {
		if (classifierId == null || classifierId.isEmpty())
			throw new IllegalArgumentException("classifierId can not be null or empty");

		HttpRequestBase request = Request.Get("/v1/classifiers/" + classifierId).build();

		try {
			HttpResponse response = execute(request);
			return ResponseUtil.getObject(response, Classifier.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}