public boolean startBlobStore(PartitionId id) {
    DiskManager diskManager = partitionToDiskManager.get(id);
    return diskManager != null && diskManager.startBlobStore(id);
  }