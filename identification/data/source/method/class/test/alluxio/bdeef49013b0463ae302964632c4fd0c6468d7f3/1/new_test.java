@Test
  public void cacheBlockTest() throws Exception {
    int fileLen = USER_QUOTA_UNIT_BYTES + 1000;

    // In order to detect whether there exists some random bugs, we reconduct the concurrent tests
    // in a loop here
    for (int round = 0; round < 10; round ++) {
      int fid = TestUtils.createByteFile(mTfs, "/cacheBlockTest", WriteType.THROUGH, fileLen);
      long usedBytes = mLocalTachyonCluster.getMasterInfo().getWorkersInfo().get(0).getUsedBytes();
      Assert.assertEquals(0, usedBytes);
      ExecutorService executor = Executors.newCachedThreadPool();
      ArrayList<Future<Void>> futures = new ArrayList<Future<Void>>(5);
      for (int i = 0; i < 5; i ++) {
        Callable<Void> call = new ConcurrentCacheBlock(fid, fileLen);
        futures.add(executor.submit(call));
      }
      for (Future<Void> f : futures) {
        f.get();
      }
      executor.shutdown();
      usedBytes = mLocalTachyonCluster.getMasterInfo().getWorkersInfo().get(0).getUsedBytes();
      Assert.assertEquals(fileLen, usedBytes);
      mTfs.delete(fid, false);
    }
  }