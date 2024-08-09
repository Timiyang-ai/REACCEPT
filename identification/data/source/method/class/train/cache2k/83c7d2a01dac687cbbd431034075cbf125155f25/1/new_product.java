public int purge() {
    lock.lock();
    try {
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
      return count;
    } finally {
      lock.unlock();
    }
  }