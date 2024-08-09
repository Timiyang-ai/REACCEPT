public SpeechResults recognize(Map<String, Object> params) {
		File audio = (File) params.get(AUDIO);
		if (audio == null || !audio.exists() || !audio.isFile())
			throw new IllegalArgumentException(
					"audio is not a valid audio file");

		String contentType = (String) params.get(CONTENT_TYPE);
		if (contentType == null)
			throw new IllegalArgumentException("contentType was not specified");

		// Build the recognize url
		StringBuilder urlBuider = new StringBuilder();
		urlBuider.append("/v1");
		urlBuider.append(params.containsKey(SESSION_ID) ? "/sessions/"
				+ params.get(SESSION_ID) : "");
		urlBuider.append("/recognize");

		Request request = Request.Post(urlBuider.toString());
		request.withHeader(HttpHeaders.CONTENT_TYPE, contentType);

		String[] queryParameters = new String[] { WORD_CONFIDENCE,
				CONTINUOUS, MAX_ALTERNATIVES, TIMESTAMPS,
				INACTIVITY_TIMEOUT, MODEL };

		for (String param : queryParameters) {
			if (params.containsKey(param))
				request.withQuery(param, params.get(param));
		}

		InputStreamEntity reqEntity = null;
		try {
			reqEntity = new InputStreamEntity(new FileInputStream(audio), -1);

			reqEntity.setContentType(contentType);
			reqEntity.setChunked(true);
			request.withEntity(reqEntity);

			HttpResponse response = execute(request.build());
			return ResponseUtil.getObject(response,SpeechResults.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}