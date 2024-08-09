public List<Classifier> getClassifiers() {
		HttpRequestBase request = Request.Get("/v1/classifiers").build();

		try {
			HttpResponse response = execute(request);
			JsonObject jsonObject = ResponseUtil.getJsonObject(response);
			List<Classifier> classifiers = GsonSingleton.getGson().fromJson(
					jsonObject.get("classifiers"), listType);
			return classifiers;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}