public ServiceCall<SpeechSessionStatus> getRecognizeStatus(final SpeechSession session) {
    Validator.notNull(session, "session cannot be null");
    Validator.notNull(session.getSessionId(), "session.sessionId cannot be null");
    
    Request request = RequestBuilder.get(String.format(PATH_SESSION_RECOGNIZE, session.getSessionId())).build();
    ResponseConverter<SpeechSessionStatus> converter = ResponseConverterUtils.getGenericObject(TYPE_SESSION_STATUS, "session");
    return createServiceCall(request,converter);
  }