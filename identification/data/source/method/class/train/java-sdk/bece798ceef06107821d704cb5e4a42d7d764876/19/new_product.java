public SpeechResults recognize(Map<String, Object> params) {
    final File audio = (File) params.get(AUDIO);
    if (audio == null || !audio.exists() || !audio.isFile())
      throw new IllegalArgumentException("audio is not a valid audio file");

    final String contentType = (String) params.get(CONTENT_TYPE);
    if (contentType == null)
      throw new IllegalArgumentException("contentType was not specified");

    // Build the recognize url
    final StringBuilder urlBuider = new StringBuilder();
    urlBuider.append("/v1");
    urlBuider.append(params.containsKey(SESSION_ID) ? "/sessions/" + params.get(SESSION_ID) : "");
    urlBuider.append("/recognize");

    final RequestBuilder requestBuilder = RequestBuilder.post(urlBuider.toString());
    requestBuilder.withHeader(HttpHeaders.CONTENT_TYPE, contentType);

    final String[] queryParameters =
        new String[] {WORD_CONFIDENCE, CONTINUOUS, MAX_ALTERNATIVES, TIMESTAMPS,
            INACTIVITY_TIMEOUT, MODEL};

    for (final String param : queryParameters) {
      if (params.containsKey(param))
        requestBuilder.withQuery(param, params.get(param));
    }

    final RequestBody body = RequestBody.create(MediaType.parse(contentType), audio);
    requestBuilder.withBody(body);
    return executeRequest(requestBuilder.build(), SpeechResults.class);
  }