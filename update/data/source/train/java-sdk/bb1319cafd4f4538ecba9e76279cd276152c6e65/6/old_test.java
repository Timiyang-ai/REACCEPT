@Test
  public void testCreateSession() {
    SpeechSession session = service.createSession();
    try {
      assertNotNull(session);
      assertNotNull(session.getSessionId());
    } finally {
      service.deleteSession(session);
    }
  }