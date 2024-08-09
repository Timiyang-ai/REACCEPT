void expire(int index, long previousTimeNanos, long currentTimeNanos) {
    Node<K, V>[] timerWheel = wheel[index];

    int start, end;
    if ((currentTimeNanos - previousTimeNanos) > SPANS[index + 1]) {
      end = timerWheel.length;
      start = 0;
    } else {
      long previousTicks = (previousTimeNanos >>> SHIFT[index]);
      long currentTicks = (currentTimeNanos >>> SHIFT[index]) + SPANS[index];
      long mask = SPANS[index] - 1;

      start = (int) (previousTicks & mask);
      end = start + 1 + (int) (currentTicks & mask);
    }

    int mask = timerWheel.length - 1;
    for (int i = start; i < end; i++) {
      Node<K, V> sentinel = timerWheel[(i & mask)];
      Node<K, V> node = sentinel.getNextInAccessOrder();
      sentinel.setPreviousInAccessOrder(sentinel);
      sentinel.setNextInAccessOrder(sentinel);

      while (node != sentinel) {
        Node<K, V> next = node.getNextInAccessOrder();
        node.setPreviousInAccessOrder(null);
        node.setNextInAccessOrder(null);

        if ((node.getAccessTime() > currentTimeNanos)
            || !cache.evictEntry(node, RemovalCause.EXPIRED, nanos)) {
          Node<K, V> newSentinel = findBucket(node.getAccessTime());
          link(newSentinel, node);
        }
        node = next;
      }
    }
  }