  @Test(timeout = 10000)
  public void lockInode() throws Exception {
    inodeLockTest(LockMode.WRITE, LockMode.READ, true);
    inodeLockTest(LockMode.READ, LockMode.WRITE, true);
    inodeLockTest(LockMode.WRITE, LockMode.WRITE, true);
    inodeLockTest(LockMode.READ, LockMode.READ, false);
  }