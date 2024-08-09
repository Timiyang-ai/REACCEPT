boolean removeBlobStore(PartitionId id) {
    rwLock.writeLock().lock();
    boolean succeed = false;
    try {
      BlobStore store = stores.get(id);
      if (store == null) {
        logger.error("Store {} is not found in disk manager", id);
      } else if (!running || store.isStarted()) {
        logger.error("Removing store {} failed. Disk running = {}, store running = {}", id, running, store.isStarted());
      } else if (!compactionManager.removeBlobStore(store)) {
        logger.error("Fail to remove store {} from compaction manager.", id);
      } else {
        stores.remove(id);
        stoppedReplicas.remove(id.toPathString());
        partitionToReplicaMap.remove(id);
        logger.info("Store {} is successfully removed from disk manager", id);
        succeed = true;
      }
    } finally {
      rwLock.writeLock().unlock();
    }
    return succeed;
  }