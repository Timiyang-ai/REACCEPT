@Test
  public void cleanupSessionsTest() {
    long sessionId = 1;
    LinkedList<Long> sessions = new LinkedList<Long>();
    sessions.add(sessionId);

    when(mSessions.getTimedOutSessions()).thenReturn(sessions);
    mBlockWorker.cleanupSessions();
    verify(mSessions).removeSession(sessionId);
    verify(mBlockStore).cleanupSession(sessionId);
  }