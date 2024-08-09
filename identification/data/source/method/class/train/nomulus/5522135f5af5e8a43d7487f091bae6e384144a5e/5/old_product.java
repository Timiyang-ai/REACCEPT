@Override
  public void run() {
    DateTime requestedEndTime = clock.nowUtc().plus(requestedMaximumDuration);
    ImmutableSet<String> tlds = Registries.getTlds();
    while (requestedEndTime.isAfterNow()) {
      List<TaskHandle> tasks = dnsQueue.leaseTasks(requestedMaximumDuration.plus(LEASE_PADDING));
      logger.infofmt("Leased %d DNS update tasks.", tasks.size());
      if (!tasks.isEmpty()) {
        dispatchTasks(ImmutableSet.copyOf(tasks), tlds);
      }
      if (tasks.size() < dnsQueue.getLeaseTasksBatchSize()) {
        return;
      }
    }
  }