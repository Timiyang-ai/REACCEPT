public List<Dialog> getDialogs() {
		HttpRequestBase request = Request.Get("/v1/dialogs").build();

		try {
			HttpResponse response = execute(request);
			JsonObject jsonObject = ResponseUtil.getJsonObject(response);
			List<Dialog> dialogs = GsonSingleton.getGson().fromJson(
					jsonObject.get("dialogs"), listDialogType);
			return dialogs;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}