@Test
  public void testClose() {
    {
      deleteFiles();
      region = DiskRegionHelperFactory.getSyncOverFlowAndPersistRegion(cache, diskProps);
      DiskRegion dr = ((LocalRegion) region).getDiskRegion();
      Oplog oplog = dr.testHook_getChild();
      long id = oplog.getOplogId();
      oplog.close();

      StatisticsFactory factory = cache.getDistributedSystem().getStatisticsFactory();
      Oplog newOplog =
          new Oplog(id, dr.getOplogSet(), new DirectoryHolder(dirs[0], 1000, 0));
      dr.getOplogSet().setChild(newOplog);
      closeDown();
    }
    {
      deleteFiles();
      region = DiskRegionHelperFactory.getSyncOverFlowOnlyRegion(cache, diskProps);
      DiskRegion dr = ((LocalRegion) region).getDiskRegion();
      dr.testHookCloseAllOverflowOplogs();
      checkIfContainsFile("OVERFLOW");
      closeDown();
    }
    {
      deleteFiles();
      region = DiskRegionHelperFactory.getSyncPersistOnlyRegion(cache, diskProps, Scope.LOCAL);
      DiskRegion dr = ((LocalRegion) region).getDiskRegion();
      Oplog oplog = dr.testHook_getChild();
      long id = oplog.getOplogId();
      oplog.close();
      StatisticsFactory factory = cache.getDistributedSystem().getStatisticsFactory();
      Oplog newOplog =
          new Oplog(id, dr.getOplogSet(), new DirectoryHolder(dirs[0], 1000, 2));
      dr.setChild(newOplog);
      closeDown();
    }

  }