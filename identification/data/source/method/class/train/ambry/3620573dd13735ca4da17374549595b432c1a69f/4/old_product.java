public boolean addBlobStore(ReplicaId replica) {
    if (partitionToDiskManager.containsKey(replica.getPartitionId())) {
      return false;
    }
    DiskManager diskManager = diskToDiskManager.computeIfAbsent(replica.getDiskId(), disk -> {
      DiskManager newDiskManager =
          new DiskManager(disk, Collections.emptyList(), storeConfig, diskManagerConfig, scheduler, metrics,
              storeMainMetrics, storeUnderCompactionMetrics, keyFactory, recovery, hardDelete, replicaStatusDelegate,
              stoppedReplicas, time);
      logger.info("Creating new DiskManager on {} for new added store", replica.getDiskId().getMountPath());
      try {
        newDiskManager.start();
      } catch (Exception e) {
        logger.error("Error while starting the new DiskManager for " + disk.getMountPath(), e);
        return null;
      }
      return newDiskManager;
    });
    if (diskManager == null || !diskManager.addBlobStore(replica)) {
      logger.error("Failed to add new store into DiskManager");
      return false;
    }
    partitionToDiskManager.put(replica.getPartitionId(), diskManager);
    logger.info("New store is successfully added into StorageManager");
    return true;
  }