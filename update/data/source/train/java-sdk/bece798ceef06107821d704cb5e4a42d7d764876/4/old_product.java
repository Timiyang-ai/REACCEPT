public Classification classify(final String classifierId, final String text) {
		if (classifierId == null || classifierId.isEmpty())
			throw new IllegalArgumentException("classifierId can not be null or empty");

		if (text == null || text.isEmpty())
			throw new IllegalArgumentException("text can not be null or empty");

		JsonObject contentJson = new JsonObject();
		contentJson.addProperty("text", text);

		String path = String.format("/v1/classifiers/%s/classify", classifierId);

		HttpRequestBase request = Request.Post(path).withContent(contentJson).build();

		try {
			HttpResponse response = execute(request);
			Classification classification =  ResponseUtil.getObject(response, Classification.class);
			
			for (ClassifiedClass klass : classification.getClasses()) {
				if (klass.getName().equals(classification.getTopClass())){
					classification.setTopConfidence(klass.getConfidence());
					break;
				}
			}
			return classification;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}