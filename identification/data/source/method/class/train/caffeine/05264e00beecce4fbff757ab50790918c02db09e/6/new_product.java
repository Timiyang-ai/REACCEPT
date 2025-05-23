void expire(int index, long previousTicks, long currentTicks) {
    Node<K, V>[] timerWheel = wheel[index];

    int start, end;
    if ((currentTicks - previousTicks) >= timerWheel.length) {
      end = timerWheel.length;
      start = 0;
    } else {
      long mask = SPANS[index] - 1;
      start = (int) (previousTicks & mask);
      end = 1 + (int) (currentTicks & mask);
    }

    int mask = timerWheel.length - 1;
    for (int i = start; i < end; i++) {
      Node<K, V> sentinel = timerWheel[(i & mask)];
      Node<K, V> prev = sentinel.getPreviousInVariableOrder();
      Node<K, V> node = sentinel.getNextInVariableOrder();
      sentinel.setPreviousInVariableOrder(sentinel);
      sentinel.setNextInVariableOrder(sentinel);

      while (node != sentinel) {
        Node<K, V> next = node.getNextInVariableOrder();
        node.setPreviousInVariableOrder(null);
        node.setNextInVariableOrder(null);

        try {
          if (((node.getVariableTime() - nanos) > 0)
              || !cache.evictEntry(node, RemovalCause.EXPIRED, nanos)) {
            schedule(node);
          }
          node = next;
        } catch (Throwable t) {
          node.setPreviousInVariableOrder(sentinel.getPreviousInVariableOrder());
          node.setNextInVariableOrder(next);
          sentinel.getPreviousInVariableOrder().setNextInVariableOrder(node);
          sentinel.setPreviousInVariableOrder(prev);
          throw t;
        }
      }
    }
  }