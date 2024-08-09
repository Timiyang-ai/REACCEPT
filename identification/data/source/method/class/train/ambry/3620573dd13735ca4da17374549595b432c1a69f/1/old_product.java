public boolean removeBlobStore(PartitionId id) {
    DiskManager diskManager = partitionToDiskManager.get(id);
    if (diskManager == null) {
      logger.info("Store {} is not found in storage manager", id);
      return true;
    }
    if (!diskManager.removeBlobStore(id)) {
      logger.error("Fail to remove store {} from disk manager", id);
      return false;
    }
    partitionToDiskManager.remove(id);
    logger.info("Store {} is successfully removed from storage manager", id);
    return true;
  }