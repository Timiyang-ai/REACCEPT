@Test(timeout = TIMEOUT_MILLIS)
    public void testRunAsync() throws Exception {
        final int invocationCount = 10;
        val count = new AtomicInteger();
        val wasInvoked = new Semaphore(0);
        val waitOn = new CompletableFuture<Void>();
        val p = new SequentialAsyncProcessor(() -> {
            count.incrementAndGet();
            wasInvoked.release();
            waitOn.join();
        }, executorService());

        // Invoke it a number of times.
        for (int i = 0; i < invocationCount; i++) {
            p.runAsync();
        }

        // Wait for at least one invocation to happen.
        wasInvoked.acquire();
        Assert.assertEquals("Task seems to have been executed concurrently.", 1, count.get());

        // Now complete the first task and ensure the subsequent requests only result in on one extra invocations.
        waitOn.complete(null);
        wasInvoked.acquire();
        Assert.assertEquals("Unexpected number of final invocations.", 2, count.get());
    }