public Classifier createClassifier(final String name, final String language, final File csvTrainingData) {
		if (csvTrainingData == null || !csvTrainingData.exists())
			throw new IllegalArgumentException("csvTrainingData can not be null or not be found");

		JsonObject contentJson = new JsonObject();

		contentJson.addProperty("language", language == null ? LANGUAGE_EN : language);

		if (name != null && !name.isEmpty()) {
			contentJson.addProperty("name", name);
		}

		try {

			MultipartEntity reqEntity = new MultipartEntity();
			reqEntity.addPart("training_data", new FileBody(csvTrainingData));
			reqEntity.addPart("training_metadata", new StringBody(contentJson.toString()));

			HttpRequestBase request = Request.Post("/v1/classifiers").withEntity(reqEntity).build();

			HttpResponse response = execute(request);
			return ResponseUtil.getObject(response, Classifier.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}