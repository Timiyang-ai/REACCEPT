public TranslationModel getModel(String modelId) {
		if (modelId == null || modelId.isEmpty())
			throw new IllegalArgumentException("model_id can not be null or empty");

		HttpRequestBase request = Request.Get("/v2/models/" + modelId).build();
		try {
			HttpResponse response = execute(request);
			String modelAsString = ResponseUtil.getString(response);
			TranslationModel model = GsonSingleton.getGson().fromJson(modelAsString, TranslationModel.class);
			return model;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}