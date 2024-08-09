  @Test(timeout = 10000)
  public void lockEdge() throws Exception {
    edgeLockTest(LockMode.WRITE, LockMode.READ, true);
    edgeLockTest(LockMode.READ, LockMode.WRITE, true);
    edgeLockTest(LockMode.WRITE, LockMode.WRITE, true);
    edgeLockTest(LockMode.READ, LockMode.READ, false);
  }