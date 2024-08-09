boolean addBlobStore(ReplicaId replica) {
    if (!running) {
      logger.error("Failed to add {} because disk manager is not running", replica.getPartitionId());
      return false;
    }
    boolean succeed = true;
    BlobStore store =
        new BlobStore(replica, storeConfig, scheduler, longLivedTaskScheduler, diskIOScheduler, diskSpaceAllocator,
            storeMainMetrics, storeUnderCompactionMetrics, keyFactory, recovery, hardDelete, replicaStatusDelegate,
            time);
    try {
      // TODO In future PR, store.start() should contain logic for recovery  OFFLINE -> BOOTSTRAP -> STANDBY
      store.start();
      // collect store segment requirements and add into DiskSpaceAllocator
      List<DiskSpaceRequirements> storeRequirements = Collections.singletonList(store.getDiskSpaceRequirements());
      diskSpaceAllocator.addRequiredSegments(diskSpaceAllocator.getOverallRequirements(storeRequirements), false);
    } catch (Exception e) {
      logger.error("Failed to start new added store {} or add requirements to disk allocator",
          replica.getPartitionId());
      succeed = false;
    }
    if (succeed) {
      // add store into CompactionManager
      compactionManager.addBlobStore(store);
      // add new created store into in-memory data structures.
      stores.put(replica.getPartitionId(), store);
      partitionToReplicaMap.put(replica.getPartitionId(), replica);
      logger.info("New store is successfully added into DiskManager.");
    }
    return succeed;
  }