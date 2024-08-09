public Tone getTone(final String text, final Scorecard scorecard) {

    if (text == null || text.isEmpty())
      throw new IllegalArgumentException("text cannot be null or empty");

    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty(TEXT, text);

    if (scorecard != null)
      contentJson.addProperty(SCORECARD, scorecard.getId());

    final Request request = RequestBuilder.post(PATH_TONE).withBodyJson(contentJson).build();
    return executeRequest(request, Tone.class);
  }