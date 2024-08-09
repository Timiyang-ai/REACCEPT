@Test
  public void testControlCompactionForBlobStore() throws Exception {
    // without compaction enabled.
    compactionManager.enable();
    assertTrue("Disable compaction on BlobStore should be true when compaction executor is not instantiated",
        compactionManager.controlCompactionForBlobStore(blobStore, false));
    compactionManager.disable();
    compactionManager.awaitTermination();
    // with compaction enabled.
    properties.setProperty("store.compaction.triggers", ALL_COMPACTION_TRIGGERS);
    config = new StoreConfig(new VerifiableProperties(properties));
    StorageManagerMetrics metrics = new StorageManagerMetrics(new MetricRegistry());
    compactionManager = new CompactionManager(MOUNT_PATH, config, Collections.singleton(blobStore), metrics, time);
    compactionManager.enable();
    assertTrue("Disable compaction on given BlobStore should succeed",
        compactionManager.controlCompactionForBlobStore(blobStore, false));
    assertFalse("BlobStore should not be scheduled after compaction is disabled on it",
        compactionManager.scheduleNextForCompaction(blobStore));
    assertTrue("Enable compaction on given BlobStore should succeed",
        compactionManager.controlCompactionForBlobStore(blobStore, true));
    assertTrue("BlobStore should be scheduled after compaction is enabled on it",
        compactionManager.scheduleNextForCompaction(blobStore));
    compactionManager.disable();
    compactionManager.awaitTermination();
  }