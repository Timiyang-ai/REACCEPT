void afterWrite(@Nullable Node<K, V> node, Runnable task, long now) {
    if (node != null) {
      node.setAccessTime(now);
      node.setWriteTime(now);
    }
    if (buffersWrites()) {
      for (int i = 0; i < WRITE_BUFFER_RETRIES; i++) {
        if (writeBuffer().offer(task)) {
          scheduleAfterWrite();
          return;
        }
        scheduleDrainBuffers();
      }

      // The maintenance task may be scheduled but not running due to all of the executor's threads
      // being busy. If all of the threads are writing into the cache then no progress can be made
      // without assistance.
      try {
        performCleanUp(task);
      } catch (RuntimeException e) {
        logger.log(Level.SEVERE, "Exception thrown when performing the maintenance task", e);
      }
    }
  }