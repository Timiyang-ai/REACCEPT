boolean shutdownBlobStore(PartitionId id) {
    rwLock.readLock().lock();
    boolean succeed = false;
    try {
      BlobStore store = stores.get(id);
      if (store == null || !running) {
        logger.error("Failed to shut down store because {} is not found or DiskManager is not running", id);
      } else if (!store.isStarted()) {
        succeed = true;
      } else {
        store.shutdown();
        succeed = true;
      }
    } catch (Exception e) {
      logger.error("Exception while shutting down store {} on disk {}", id, disk, e);
    } finally {
      rwLock.readLock().unlock();
    }
    return succeed;
  }