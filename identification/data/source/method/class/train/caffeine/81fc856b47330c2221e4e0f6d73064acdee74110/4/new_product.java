void afterWrite(@Nullable Node<K, V> node, Runnable task, long now) {
    if (node != null) {
      node.setAccessTime(now);
      node.setWriteTime(now);
    }
    if (buffersWrites()) {
      boolean submitted = false;
      for (;;) {
        for (int i = 0; i < WRITE_BUFFER_RETRIES; i++) {
          submitted = writeBuffer().offer(task);
          if (submitted) {
            break;
          }
          scheduleDrainBuffers();
        }
        if (submitted) {
          break;
        } else {
          Thread.yield();
        }
      }
    }
    scheduleAfterWrite();
  }