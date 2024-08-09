  private void release(Lock lock, String expectedTld, long expectedMillis) {
    Lock.lockMetrics = mock(LockMetrics.class);
    lock.release();
    verify(Lock.lockMetrics)
        .recordRelease(RESOURCE_NAME, expectedTld, Duration.millis(expectedMillis));
    verifyNoMoreInteractions(Lock.lockMetrics);
    Lock.lockMetrics = null;
  }