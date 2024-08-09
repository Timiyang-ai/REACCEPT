@Test
  public void downgradeToNoWriteLocksTest() throws InterruptedException {
    final CountDownLatch sync = new CountDownLatch(1), test = new CountDownLatch(1);

    final LockTester th1 = new LockTester(null, NONE, objects, sync);
    final LockTester th2 = new LockTester(sync, null, NONE, test);

    th1.start();
    th2.start();
    assertFalse("Thread 2 shouldn't be able to acquire lock yet.",
        test.await(WAIT, TimeUnit.MILLISECONDS));
    th1.downgrade(NONE);
    assertTrue("Thread 2 should be able to acquire lock now.",
        test.await(WAIT, TimeUnit.MILLISECONDS));
    th1.release();
    th2.release();
  }