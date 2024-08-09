public boolean controlCompactionForBlobStore(PartitionId id, boolean enabled) {
    DiskManager diskManager = partitionToDiskManager.get(id);
    return diskManager != null && diskManager.controlCompactionForBlobStore(id, enabled);
  }