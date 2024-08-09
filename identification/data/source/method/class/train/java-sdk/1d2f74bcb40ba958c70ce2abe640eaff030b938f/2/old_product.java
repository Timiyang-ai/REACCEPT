public ServiceCall<SessionStatus> getRecognizeStatus(final SpeechSession session) {
    if (session == null)
      throw new IllegalArgumentException("Session was not specified");

    Request request = RequestBuilder.get(String.format(PATH_SESSION_RECOGNIZE, session.getSessionId())).build();
    return createServiceCall(request, ResponseConverterUtils.getObject(SessionStatus.class));
  }