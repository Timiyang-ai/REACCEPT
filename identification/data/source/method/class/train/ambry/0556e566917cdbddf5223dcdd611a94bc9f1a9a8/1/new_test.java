  @Test
  public void deleteStoreFilesTest() throws Exception {
    store.shutdown();
    // create test store directory
    File storeDir = StoreTestUtils.createTempDirectory("store-" + storeId);
    File reserveDir = StoreTestUtils.createTempDirectory("reserve-pool");
    DiskSpaceAllocator diskAllocator =
        new DiskSpaceAllocator(true, reserveDir, 0, new StorageManagerMetrics(new MetricRegistry()));
    StoreConfig config = new StoreConfig(new VerifiableProperties(properties));
    MetricRegistry registry = new MetricRegistry();
    StoreMetrics metrics = new StoreMetrics(registry);
    BlobStore testStore =
        new BlobStore(getMockReplicaId(storeDir.getAbsolutePath()), config, scheduler, storeStatsScheduler,
            diskIOScheduler, diskAllocator, metrics, metrics, STORE_KEY_FACTORY, recovery, hardDelete, null, time);
    testStore.start();
    DiskSpaceRequirements diskSpaceRequirements = testStore.getDiskSpaceRequirements();
    diskAllocator.initializePool(diskSpaceRequirements == null ? Collections.emptyList()
        : Collections.singletonList(testStore.getDiskSpaceRequirements()));
    // ensure store directory and file exist
    assertTrue("Store directory doesn't exist", storeDir.exists());
    File storeSegmentDir = new File(reserveDir, DiskSpaceAllocator.STORE_DIR_PREFIX + storeId);
    if (isLogSegmented) {
      assertTrue("Store segment directory doesn't exist", storeSegmentDir.exists());
      assertTrue("In-mem store file map should contain entry associated with test store",
          diskAllocator.getStoreReserveFileMap().containsKey(storeId));
    }
    // test that deletion on started store should fail
    try {
      testStore.deleteStoreFiles();
    } catch (IllegalStateException e) {
      //expected
    }
    // create a unreadable dir in store dir to induce deletion failure
    File invalidDir = new File(storeDir, "invalidDir");
    assertTrue("Couldn't create dir within store dir", invalidDir.mkdir());
    assertTrue("Could not make unreadable", invalidDir.setReadable(false));
    testStore.shutdown();
    try {
      testStore.deleteStoreFiles();
      fail("should fail because one invalid dir is unreadable");
    } catch (Exception e) {
      // expected
    }
    assertTrue("store directory should exist because deletion failed", storeDir.exists());
    // reset permission to allow deletion to succeed.
    assertTrue("Could not make readable", invalidDir.setReadable(true));

    // put a swap segment into store dir
    File tempFile = File.createTempFile("sample-swap",
        LogSegmentNameHelper.SUFFIX + BlobStoreCompactor.TEMP_LOG_SEGMENT_NAME_SUFFIX, storeDir);
    // test success case (swap segment is returned and store dir is correctly deleted)
    assertEquals("Swap reserve dir should be empty initially", 0,
        diskAllocator.getSwapReserveFileMap().getFileSizeSet().size());
    testStore.deleteStoreFiles();
    assertFalse("swap segment still exists", tempFile.exists());
    assertEquals("Swap reserve dir should have one swap segment", 1,
        diskAllocator.getSwapReserveFileMap().getFileSizeSet().size());
    assertFalse("store directory shouldn't exist", storeDir.exists());
    assertFalse("store segment directory shouldn't exist", storeSegmentDir.exists());
    assertFalse("test store entry should have been removed from in-mem store file map ",
        diskAllocator.getStoreReserveFileMap().containsKey(storeId));
    reloadStore();
  }