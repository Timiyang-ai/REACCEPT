@Test
  public void testCreateSession() {
    SpeechSession session = service.createSession().execute();
    try {
      assertNotNull(session);
      assertNotNull(session.getSessionId());
    } finally {
      service.deleteSession(session).execute();
    }
  }