  private Optional<Lock> acquire(String tld, Duration leaseLength, LockState expectedLockState) {
    Lock.lockMetrics = mock(LockMetrics.class);
    Optional<Lock> lock = Lock.acquire(RESOURCE_NAME, tld, leaseLength, requestStatusChecker, true);
    verify(Lock.lockMetrics).recordAcquire(RESOURCE_NAME, tld, expectedLockState);
    verifyNoMoreInteractions(Lock.lockMetrics);
    Lock.lockMetrics = null;
    return lock;
  }