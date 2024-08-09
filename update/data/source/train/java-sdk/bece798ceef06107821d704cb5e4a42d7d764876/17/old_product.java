public Job createJob(final String label, final String[] seeds) {
		if (dataset == null)
			throw new IllegalArgumentException("dataset can not be null or empty");
		if (label == null)
			throw new IllegalArgumentException("label can not be null or empty");
		if (seeds == null || seeds.length == 0)
			throw new IllegalArgumentException("seeds can not be null or empty");

		try {
			JsonArray seedJsonArray = new JsonArray();
			for (String seed : seeds) {
				seedJsonArray.add(new JsonPrimitive(seed));
			}

			JsonObject payload = new JsonObject();
			payload.addProperty(LABEL, label);
			payload.addProperty(DATASET, dataset.getId());
			payload.add(SEEDS,seedJsonArray);

			HttpRequestBase request = Request.Post("/v1/upload")
					.withContent(payload).build();

			HttpResponse response = execute(request);
			String jsonJob = ResponseUtil.getString(response);
			return GsonSingleton.getGson().fromJson(jsonJob,Job.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}