@GuardedBy("evictionLock")
  void evict() {
    // Attempts to evict entries from the map if it exceeds the maximum
    // capacity. If the eviction fails due to a concurrent removal of the
    // victim, that removal may cancel out the addition that triggered this
    // eviction. The victim is eagerly unlinked before the removal task so
    // that if an eviction is still required then a new victim will be chosen
    // for removal.
    while (hasOverflowed()) {
      final Node<K, V> node = evictionDeque.poll();

      // If weighted values are used, then the pending operations will adjust
      // the size to reflect the correct weight
      if (node == null) {
        return;
      }

      // Notify the listener only if the entry was evicted
      if (data.remove(node.key, node) && (removalListener != null)) {
        executor.execute(() -> {
          RemovalNotification<K, V> notification = new RemovalNotification<K, V>(
              node.key, node.getValue(), RemovalCause.SIZE);
          removalListener.onRemoval(notification);
        });
      }

      makeDead(node);
    }
  }