public List<Voice> getVoices() {
		HttpRequestBase request = Request.Get("/v1/voices").build();
		try {
			HttpResponse response = execute(request);
			JsonObject jsonObject = ResponseUtil.getJsonObject(response);
			List<Voice> voices = GsonSingleton.getGson().fromJson(
					jsonObject.get("voices"), listVoiceType);
			return voices;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}