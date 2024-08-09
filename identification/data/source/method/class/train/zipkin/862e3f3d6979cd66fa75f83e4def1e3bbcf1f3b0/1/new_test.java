  @Test public void execute_isThrottled() throws Exception {
    int queueSize = 1;
    int totalTasks = numThreads + queueSize;
    limit.setLimit(totalTasks);

    Semaphore startLock = new Semaphore(numThreads);
    Semaphore waitLock = new Semaphore(totalTasks);
    Semaphore failLock = new Semaphore(1);
    ThrottledCall throttled = throttle(new LockedCall(startLock, waitLock));

    // Step 1: drain appropriate locks
    startLock.drainPermits();
    waitLock.drainPermits();
    failLock.drainPermits();

    // Step 2: saturate threads and fill queue
    ExecutorService backgroundPool = Executors.newCachedThreadPool();
    for (int i = 0; i < totalTasks; i++) {
      backgroundPool.submit(() -> throttled.clone().execute());
    }

    try {
      // Step 3: make sure the threads actually started
      startLock.acquire(numThreads);

      // Step 4: submit something beyond our limits
      Future<?> future = backgroundPool.submit(() -> {
        try {
          throttled.execute();
        } catch (IOException e) {
          throw new UncheckedIOException(e);
        } finally {
          // Step 6: signal that we tripped the limit
          failLock.release();
        }
      });

      // Step 5: wait to make sure our limit actually tripped
      failLock.acquire();

      future.get();

      // Step 7: Expect great things
      failBecauseExceptionWasNotThrown(ExecutionException.class);
    } catch (ExecutionException t) {
      assertThat(t)
        .isInstanceOf(ExecutionException.class) // from future.get
        .hasCauseInstanceOf(RejectedExecutionException.class);
    } finally {
      waitLock.release(totalTasks);
      startLock.release(totalTasks);
      backgroundPool.shutdownNow();
    }
  }