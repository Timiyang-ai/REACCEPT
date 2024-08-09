public InProcessServerBuilder scheduledExecutorService(
      ScheduledExecutorService scheduledExecutorService) {
    schedulerPool = new FixedObjectPool<ScheduledExecutorService>(
        checkNotNull(scheduledExecutorService, "scheduledExecutorService"));
    return this;
  }