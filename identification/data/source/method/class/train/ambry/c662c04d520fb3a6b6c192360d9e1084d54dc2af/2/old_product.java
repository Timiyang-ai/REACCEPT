boolean shutdownBlobStore(PartitionId id) {
    BlobStore store = (BlobStore) getStore(id);
    if (store == null) {
      return false;
    }
    try {
      store.shutdown();
    } catch (Exception e) {
      logger.error("Exception while shutting down store {} on disk {}", id, disk, e);
      return false;
    }
    return true;
  }