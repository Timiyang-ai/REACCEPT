public void throwIfNotInThisSynchronizationContext() {
    synchronized (lock) {
      checkState(
          Thread.currentThread() == drainingThread,
          "Not called from the SynchronizationContext");
    }
  }