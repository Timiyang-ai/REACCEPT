public SpeechResults recognize(File audio, String contentType, RecognizeOptions options) {
    Validate.isTrue(audio != null && audio.exists(), "audio file is null or does not exist");
    Validate.isTrue(audio != null && audio.exists(), "audio file is null or does not exist");

    Validate.isTrue((audio.length() / (1024 * 1024)) < 100.0,
        "The audio file is greater than 100MB.");

    Validate.isTrue(MediaType.parse(contentType) != null,
        "contentType is not a valid mime audio format. Valid formats start with 'audio/'");

    String path = PATH_RECOGNIZE;
    if (options != null && (options.getSessionId() != null && !options.getSessionId().isEmpty()))
      path = String.format(PATH_SESSION_RECOGNIZE, options.getSessionId());

    final RequestBuilder requestBuilder = RequestBuilder.post(path);

    buildRecognizeRequest(requestBuilder, options);

    requestBuilder.withBody(RequestBody.create(MediaType.parse(contentType), audio));
    return executeRequest(requestBuilder.build(), SpeechResults.class);
  }