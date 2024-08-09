@GuardedBy("evictionLock")
  void evict() {
    if (!evicts()) {
      return;
    }

    // Attempts to evict entries from the map if it exceeds the maximum capacity. If the eviction
    // fails due to a concurrent removal of the victim, that removal may cancel out the addition
    // that triggered this eviction. The victim is eagerly unlinked before the removal task so
    // that if an eviction is still required then a new victim will be chosen for removal.
    Node<K, V> node = accessOrderDeque.peek();
    while (hasOverflowed()) {
      // If weighted values are used, then the pending operations will adjust the size to reflect
      // the correct weight
      if (node == null) {
        return;
      }

      Node<K, V> next = node.getNextInAccessOrder();
      if (node.getWeight() != 0) {
        evict(node, RemovalCause.SIZE);
      }
      node = next;
    }
  }