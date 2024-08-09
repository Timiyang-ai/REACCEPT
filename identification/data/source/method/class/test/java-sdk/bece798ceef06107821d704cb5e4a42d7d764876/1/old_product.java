public InputStream synthesize(final String text, final Voice voice, final String format) {
		if (text == null)
			throw new IllegalArgumentException("text can not be null");
		if (voice == null)
			throw new IllegalArgumentException("voice can not be null");

		Request request = Request.Get("/v1/synthesize");
		request.withQuery("text", text);
		request.withQuery("voice", voice.getName());

		if (format != null && !format.startsWith("audio/"))
			throw new IllegalArgumentException(
					"format needs to be an audio mime type, for example: audio/wav or audio/ogg; codecs=opus");

		request.withQuery("accept", format != null ? format
				: MediaType.AUDIO_WAV);

		try {
			HttpResponse response = execute(request.build());
			InputStream is = ResponseUtil.getInputStream(response);
			return is;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}