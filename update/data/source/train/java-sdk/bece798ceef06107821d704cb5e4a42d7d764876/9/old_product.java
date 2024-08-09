public Tone getTone(final String text, final Scorecard scorecard) {

		if (text == null || text.isEmpty())
			throw new IllegalArgumentException("text can not be null or empty");

		JsonObject contentJson = new JsonObject();
		contentJson.addProperty(TEXT, text);

		if (scorecard != null)
			contentJson.addProperty(SCORECARD, scorecard.getId());

		HttpRequestBase request = Request.Post("/v1/tone").withContent(contentJson).build();

		try {
			HttpResponse response = execute(request);
			String toneAsJson = ResponseUtil.getString(response);
			Tone tone = GsonSingleton.getGson().fromJson(toneAsJson, Tone.class);
			return tone;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}