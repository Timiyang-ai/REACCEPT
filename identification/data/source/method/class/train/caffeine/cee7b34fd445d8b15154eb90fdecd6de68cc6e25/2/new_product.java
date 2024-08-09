@SuppressWarnings("IntLongMath")
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
        if (next == sentinel) {
          continue;
        }
        long buckets = (j - start);
        long delay = (buckets << SHIFT[i]) - (nanos & spanMask);
        delay = (delay > 0) ? delay : SPANS[i];

        for (int k = i + 1; k < SHIFT.length; k++) {
          long nextDelay = peekAhead(k);
          delay = Math.min(delay, nextDelay);
        }

        return delay;
      }
    }
    return Long.MAX_VALUE;
  }