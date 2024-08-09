public void throwIfNotInThisSynchronizationContext() {
    checkState(Thread.currentThread() == drainingThread.get(),
        "Not called from the SynchronizationContext");
  }