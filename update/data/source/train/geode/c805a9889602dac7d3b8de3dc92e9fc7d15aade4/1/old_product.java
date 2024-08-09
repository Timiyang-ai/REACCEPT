public void close() {
    synchronized (closed) {
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