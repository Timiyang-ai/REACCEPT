void shutdown() throws InterruptedException {
    long startTimeMs = time.milliseconds();
    try {
      compactionManager.disable();
      diskIOScheduler.disable();
      final AtomicInteger numFailures = new AtomicInteger(0);
      List<Thread> shutdownThreads = new ArrayList<>();
      for (final Map.Entry<PartitionId, BlobStore> partitionAndStore : stores.entrySet()) {
        Thread thread = Utils.newThread("store-shutdown-" + partitionAndStore.getKey(), new Runnable() {
          @Override
          public void run() {
            try {
              partitionAndStore.getValue().shutdown();
            } catch (Exception e) {
              numFailures.incrementAndGet();
              metrics.totalStoreShutdownFailures.inc();
              logger.error("Exception while shutting down store {} on disk {}", partitionAndStore.getKey(), disk, e);
            }
          }
        }, false);
        thread.start();
        shutdownThreads.add(thread);
      }
      for (Thread shutdownThread : shutdownThreads) {
        shutdownThread.join();
      }
      if (numFailures.get() > 0) {
        logger.error(
            "Could not shutdown " + numFailures.get() + " out of " + stores.size() + " stores on the disk " + disk);
      }
      compactionManager.awaitTermination();
      longLivedTaskScheduler.shutdown();
      if (!longLivedTaskScheduler.awaitTermination(30, TimeUnit.SECONDS)) {
        logger.error("Could not terminate long live tasks after DiskManager shutdown");
      }
    } finally {
      metrics.diskShutdownTimeMs.update(time.milliseconds() - startTimeMs);
    }
  }