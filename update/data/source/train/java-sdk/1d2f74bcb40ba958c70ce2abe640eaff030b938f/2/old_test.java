@Test
  public void testGetRecognizeStatus() {
    SpeechSession session = service.createSession(SpeechModel.EN_BROADBAND16K).execute();
    SessionStatus status = service.getRecognizeStatus(session).execute();
    try {
      assertNotNull(status);
      assertNotNull(status.getModel());
      assertNotNull(status.getState());
    } finally {
      service.deleteSession(session).execute();
    }
  }