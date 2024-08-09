public long getExpirationDelay() {
    for (int i = 0; i < SHIFT.length; i++) {
      Node<K, V>[] timerWheel = wheel[i];
      long ticks = (nanos >>> SHIFT[i]);

      long spanMask = SPANS[i] - 1;
      int start = (int) (ticks & spanMask);
      int end = start + timerWheel.length;
      int mask = timerWheel.length - 1;
      for (int j = start; j < end; j++) {
        Node<K, V> sentinel = timerWheel[(j & mask)];
        Node<K, V> next = sentinel.getNextInVariableOrder();
        if (sentinel != next) {
          long delay = ((j - start) * SPANS[i]) - (nanos & spanMask);
          return (delay > 0) ? delay : SPANS[i];
        }
      }
    }
    return Long.MAX_VALUE;
  }