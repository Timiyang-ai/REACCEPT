@Test
  public void stopTest() throws Exception {
    ExecutorService service =
        (ExecutorService) Whitebox.getInternalState(mFileSystemMaster, "mExecutorService");
    Future<?> ttlThread =
        (Future<?>) Whitebox.getInternalState(mFileSystemMaster, "mTtlCheckerService");
    Assert.assertFalse(ttlThread.isDone());
    Assert.assertFalse(service.isShutdown());
    mFileSystemMaster.stop();
    Assert.assertTrue(ttlThread.isDone());
    Assert.assertTrue(service.isShutdown());
  }