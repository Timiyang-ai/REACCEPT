public boolean disableCompactionForBlobStore(PartitionId id) {
    DiskManager diskManager = partitionToDiskManager.get(id);
    return diskManager != null && diskManager.disableCompactionForBlobStore(id);
  }