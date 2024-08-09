void expire(int index, long previousTimeNanos, long currentTimeNanos) {
    Node<K, V>[] timerWheel = wheel[index];

    int start, end;
    if ((currentTimeNanos - previousTimeNanos) > SPANS[index + 1]) {
      end = timerWheel.length - 1;
      start = 0;
    } else {
      long previousTicks = (previousTimeNanos >>> SHIFT[index]);
      long currentTicks = (currentTimeNanos >>> SHIFT[index]) + SPANS[index];
      long mask = SPANS[index] - 1;

      end = (int) (currentTicks & mask);
      start = (int) (previousTicks & mask);
    }

    int mask = timerWheel.length - 1;
    int range = 1 + Math.abs(end - start);
    for (int i = 0; i < range; i++) {
      int bucket = (i + start) & mask;
      Node<K, V> sentinel = timerWheel[bucket];
      Node<K, V> node = sentinel.getNextInAccessOrder();
      sentinel.setPreviousInAccessOrder(sentinel);
      sentinel.setNextInAccessOrder(sentinel);

      while (node != sentinel) {
        Node<K, V> next = node.getNextInAccessOrder();
        node.setPreviousInAccessOrder(null);
        node.setNextInAccessOrder(null);

        if ((node.getAccessTime() > currentTimeNanos) || !evictor.test(node)) {
          Node<K, V> newSentinel = findBucket(node.getAccessTime());
          link(newSentinel, node);
        }
        node = next;
      }
    }
  }