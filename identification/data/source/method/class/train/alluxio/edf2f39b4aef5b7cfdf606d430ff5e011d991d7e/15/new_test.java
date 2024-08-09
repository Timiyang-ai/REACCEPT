  @Test
  public void sessionHeartbeat() {
    long sessionId = mRandom.nextLong();

    mBlockWorker.sessionHeartbeat(sessionId);
    verify(mSessions).sessionHeartbeat(sessionId);
  }