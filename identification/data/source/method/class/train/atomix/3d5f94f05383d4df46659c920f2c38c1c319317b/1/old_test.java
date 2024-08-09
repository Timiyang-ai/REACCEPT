@Test(timeout = 10000)
  public void testQueueStatus() throws Exception {
    Atomix atomix = atomix();
    AsyncDistributedSemaphore semaphore = atomix.semaphoreBuilder("test-semaphore-status", protocol())
            .withInitialCapacity(10)
            .build()
            .async();

    semaphore.acquire(5).join();

    Versioned<QueueStatus> status = semaphore.queueStatus().get();
    assertEquals(0, status.value().queueLength());
    assertEquals(0, status.value().totalPermits());

    CompletableFuture<Version> acquire6 = semaphore.acquire(6);
    Versioned<QueueStatus> status2 = semaphore.queueStatus().get();
    assertEquals(1, status2.value().queueLength());
    assertEquals(6, status2.value().totalPermits());


    CompletableFuture<Version> acquire10 = semaphore.acquire(10);
    QueueStatus status3 = semaphore.queueStatus().get().value();
    assertEquals(2, status3.queueLength());
    assertEquals(16, status3.totalPermits());

    semaphore.release().join();
    acquire6.join();

    QueueStatus status4 = semaphore.queueStatus().get().value();
    assertEquals(1, status4.queueLength());
    assertEquals(10, status4.totalPermits());

    semaphore.release(10).join();
    acquire10.join();

    QueueStatus status5 = semaphore.queueStatus().get().value();
    assertEquals(0, status5.queueLength());
    assertEquals(0, status5.totalPermits());
  }