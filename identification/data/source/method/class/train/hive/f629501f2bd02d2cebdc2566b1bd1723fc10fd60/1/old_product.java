@Override
  public synchronized void setCapacity(int newNumExecutors, int newWaitQueueSize) {
    if (newNumExecutors > configuredMaxExecutors) {
      throw new IllegalArgumentException("Requested newNumExecutors=" + newNumExecutors
          + " is greater than the configured maximum=" + configuredMaxExecutors);
    }
    if (newWaitQueueSize > configuredWaitingQueueSize) {
      throw new IllegalArgumentException("Requested newWaitQueueSize=" + newWaitQueueSize
          + " is greater than the configured maximum=" + configuredWaitingQueueSize);
    }
    if (newNumExecutors < 0) {
      throw new IllegalArgumentException("Negative numExecutors is not allowed. Requested "
          + "newNumExecutors=" + newNumExecutors);
    }
    if (newWaitQueueSize < 0) {
      throw new IllegalArgumentException("Negative waitQueueSize is not allowed. Requested "
          + "newWaitQueueSize=" + newWaitQueueSize);
    }
    numSlotsAvailable.addAndGet(newNumExecutors - maxParallelExecutors);
    maxParallelExecutors = newNumExecutors;
    waitQueue.setWaitQueueSize(newWaitQueueSize);
    LOG.info("TaskExecutorService is setting capacity to: numExecutors=" + newNumExecutors
        + ", waitQueueSize=" + newWaitQueueSize);
  }