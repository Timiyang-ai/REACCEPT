@Test
  public void cleanupSessionsTest() throws Exception {
    long sessionId = 1;
    LinkedList<Long> sessions = new LinkedList<Long>();
    sessions.add(sessionId);

    when(mSessions.getTimedOutSessions()).thenReturn(sessions);
    Whitebox.invokeMethod(mBlockWorker, "setupSessionCleaner");
    SessionCleaner cleaner = Whitebox.getInternalState(mBlockWorker, "mSessionCleaner");
    SessionCleanable cleanable = Whitebox.getInternalState(cleaner, "mSessionCleanable");
    cleanable.cleanupSessions();
    verify(mSessions).removeSession(sessionId);
    verify(mBlockStore).cleanupSession(sessionId);
  }