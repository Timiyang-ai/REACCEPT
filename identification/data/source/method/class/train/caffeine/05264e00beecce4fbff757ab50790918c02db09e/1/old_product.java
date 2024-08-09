void expire(int index, long previousTimeNanos, long currentTimeNanos) {
    int start, end;
    if ((currentTimeNanos - previousTimeNanos) > SPANS[index + 1]) {
      end = SPOKES[index] - 1;
      start = 0;
    } else {
      long mask = SPANS[index] - 1;
      long previousTicks = (previousTimeNanos >>> SHIFT[index]);
      long currentTicks = (currentTimeNanos >>> SHIFT[index]);

      end = (int) (currentTicks & mask);
      start = (int) (previousTicks & mask);
    }

    Node<K, V>[] timerWheel = wheel[index];
    for (int i = start; i <= end; i++) {
      Node<K, V> node = timerWheel[i].getNextInAccessOrder();
      timerWheel[i].setPreviousInAccessOrder(timerWheel[i]);
      timerWheel[i].setNextInAccessOrder(timerWheel[i]);

      while (node != timerWheel[i]) {
        Node<K, V> next = node.getNextInAccessOrder();
        if ((node.getAccessTime() > currentTimeNanos) || !evictor.test(node)) {
          schedule(node);
        }
        node = next;
      }
    }
  }