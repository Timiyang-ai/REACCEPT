@Test
  public void shutdownTest() throws StoreException {
    store.shutdown();
    // no operations should be possible if store is not up or has been shutdown
    verifyOperationFailuresOnInactiveStore(store);
    StorageManagerMetrics metrics = new StorageManagerMetrics(new MetricRegistry());
    StoreConfig config = new StoreConfig(new VerifiableProperties(properties));
    store = new BlobStore(storeId, config, scheduler, storeStatsScheduler, diskIOScheduler, metrics, tempDirStr,
        LOG_CAPACITY, STORE_KEY_FACTORY, recovery, hardDelete, time);
    verifyOperationFailuresOnInactiveStore(store);
  }