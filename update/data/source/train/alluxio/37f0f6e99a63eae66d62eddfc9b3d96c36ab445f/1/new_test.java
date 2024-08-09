@Test
  public void sessionHeartbeat() {
    long sessionId = mRandom.nextLong();
    long metricIncrease = 3;

    mBlockWorker.sessionHeartbeat(sessionId);
    verify(mSessions).sessionHeartbeat(sessionId);
  }