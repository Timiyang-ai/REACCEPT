@Test
  public void testCreateSession() {
    SpeechSession session = service.createSession().execute();
    try {
      assertNotNull(session);
      assertNotNull(session.getSessionId());
    } finally {
      DeleteSessionOptions deleteOptions = new DeleteSessionOptions.Builder()
          .sessionId(session.getSessionId())
          .build();
      service.deleteSession(deleteOptions).execute();
    }
  }