@Test
  public void sessionHeartbeat() {
    long sessionId = mRandom.nextLong();
    long metricIncrease = 3;
    List<Long> metrics = Arrays.asList(new Long[Constants.CLIENT_METRICS_SIZE]);
    Collections.fill(metrics, metricIncrease);
    metrics.set(0, Constants.CLIENT_METRICS_VERSION);

    mBlockWorker.sessionHeartbeat(sessionId, metrics);
    verify(mSessions).sessionHeartbeat(sessionId);
    Counter counter = WorkerContext.getWorkerSource().getMetricRegistry().getCounters()
        .get(WorkerSource.BLOCKS_READ_LOCAL);
    assertEquals(metricIncrease, counter.getCount());
  }