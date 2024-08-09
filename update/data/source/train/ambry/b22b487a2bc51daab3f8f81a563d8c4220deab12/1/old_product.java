boolean removeBlobStore(PartitionId id) {
    BlobStore store = stores.get(id);
    if (store == null) {
      logger.info("Store {} is not found in disk manager", id);
      return true;
    }
    if (!running || store.isStarted()) {
      logger.error("Removing store {} failed. Disk running = {}, store running = {}", id, running, store.isStarted());
      return false;
    }
    if (!compactionManager.removeBlobStore(store)) {
      logger.error("Fail to remove store {} from compaction manager.", id);
      return false;
    }
    stores.remove(id);
    stoppedReplicas.remove(id.toPathString());
    partitionToReplicaMap.remove(id);
    logger.info("Store {} is successfully removed from disk manager", id);
    return true;
  }