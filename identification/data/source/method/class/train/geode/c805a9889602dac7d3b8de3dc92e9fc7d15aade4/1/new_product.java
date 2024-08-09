public void close() {
    synchronized (this) {
      if (!this.closed) {
        this.closed = true;
      } else {
        return;
      }
    }
    for (ExecutorService executorService : asyncCloseExecutors.values()) {
      executorService.shutdown();
    }
    asyncCloseExecutors.clear();
  }