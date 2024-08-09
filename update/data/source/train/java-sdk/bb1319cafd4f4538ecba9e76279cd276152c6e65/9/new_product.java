public ServiceCall<SessionStatus> getRecognizeStatus(final SpeechSession session) {
    if (session == null)
      throw new IllegalArgumentException("Session was not specified");

    final okhttp3.Request request = RequestBuilder.get(String.format(PATH_SESSION_RECOGNIZE, session.getSessionId())).build3();
    return createServiceCall(createCall(request), ResponseUtil.getObjectConverter(SessionStatus.class));
  }