public InputStream synthesize(final String text, final Voice voice, final String format) {
    if (text == null)
      throw new IllegalArgumentException("text cannot be null");
    if (voice == null)
      throw new IllegalArgumentException("voice cannot be null");

    final RequestBuilder request = RequestBuilder.get("/v1/synthesize");
    request.withQuery("text", text);
    request.withQuery("voice", voice.getName());

    if (format != null && !format.startsWith("audio/"))
      throw new IllegalArgumentException(
          "format needs to be an audio mime type, for example: audio/wav or audio/ogg; codecs=opus");

    request.withQuery("accept", format != null ? format : HttpMediaType.AUDIO_WAV);

    final Response response = execute(request.build());
    return ResponseUtil.getInputStream(response);
  }