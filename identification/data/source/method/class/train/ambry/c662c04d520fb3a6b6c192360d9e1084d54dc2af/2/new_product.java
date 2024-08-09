boolean shutdownBlobStore(PartitionId id) {
    BlobStore store = stores.get(id);
    if (store == null || !running) {
      return false;
    } else if (!store.isStarted()) {
      return true;
    } else {
      try {
        store.shutdown();
      } catch (Exception e) {
        logger.error("Exception while shutting down store {} on disk {}", id, disk, e);
        return false;
      }
      return true;
    }
  }