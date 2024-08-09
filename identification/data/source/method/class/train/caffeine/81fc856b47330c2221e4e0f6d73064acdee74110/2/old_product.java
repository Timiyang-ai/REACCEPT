void afterWrite(@Nullable Node<K, V> node, Runnable task, long now) {
    if (node != null) {
      node.setAccessTime(now);
      node.setWriteTime(now);
    }
    if (buffersWrites()) {
      writeQueue().add(task);
    }
    lazySetDrainStatus(REQUIRED);
    scheduleDrainBuffers();
  }