public SessionStatus getRecognizeStatus(final SpeechSession session) {
    if (session == null)
      throw new IllegalArgumentException("session was not specified");

    final Request request =
        RequestBuilder.get(String.format(PATH_SESSION_RECOGNIZE, session.getSessionId())).build();
    final Response response = execute(request);
    final JsonObject jsonObject = ResponseUtil.getJsonObject(response);
    return GsonSingleton.getGsonWithoutPrettyPrinting().fromJson(jsonObject.get(SESSION), SessionStatus.class);
  }