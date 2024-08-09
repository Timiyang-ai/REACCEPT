public String extract(final String text) {
		if (dataset == null)
			throw new IllegalArgumentException("dataset can not be null");
		if (text == null)
			throw new IllegalArgumentException("text can not be null");

		HttpRequestBase request = Request.Post("/v1/sire/0")
				.withForm("sid", dataset.getId(), "rt", "xml", "txt", text).build();
		String relations = null;
		try {
			HttpResponse response = execute(request);
			relations = ResponseUtil.getString(response);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return relations;
	}