public ToneAnalysis getTone(final String text) {

    if (text == null || text.isEmpty())
      throw new IllegalArgumentException("text cannot be null or empty");

    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty(TEXT, text);

    final Request request = RequestBuilder.post(PATH_TONE).withBodyJson(contentJson).build();
    return executeRequest(request, ToneAnalysis.class);
  }