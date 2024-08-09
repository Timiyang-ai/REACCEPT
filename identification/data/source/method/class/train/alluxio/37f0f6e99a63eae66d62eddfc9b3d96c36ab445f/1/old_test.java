@Test
  public void sessionHeartbeat() {
    long sessionId = mRandom.nextLong();
    long metricIncrease = 3;

    mBlockWorker.sessionHeartbeat(sessionId, null);
    verify(mSessions).sessionHeartbeat(sessionId);
    Counter counter = WorkerContext.getWorkerSource().getMetricRegistry().getCounters()
        .get(WorkerSource.BLOCKS_READ_LOCAL);
    assertEquals(metricIncrease, counter.getCount());
  }