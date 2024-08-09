public int purge() {
    final AtomicInteger result = new AtomicInteger();

    clock.runExclusive(notifier, new Runnable() {
      @Override
      public void run() {
        int count = 0;
        for (int i = queue.size(); i > 0; i--) {
          if (queue.get(i).isCancelled()) {
            queue.quickRemove(i);
            count++;
          }
        }

        if (count != 0) {
          queue.heapify();
        }
        result.set(count);
      }
    });
    return result.get();
  }