public InProcessServerBuilder scheduledExecutorService(
      ScheduledExecutorService scheduledExecutorService) {
    schedulerPool = new FixedObjectPool<>(
        checkNotNull(scheduledExecutorService, "scheduledExecutorService"));
    return this;
  }