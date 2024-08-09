@Test
    public void testThenAcceptBoth() throws Exception {
        LinkedBlockingQueue<Object> results = new LinkedBlockingQueue<Object>();
        String currentThreadName = Thread.currentThread().getName();

        BiConsumer<Integer, Integer> action = (a, b) -> {
            System.out.println("> sum " + a + "+" + b + " from testThenAcceptBoth");
            results.add(a + b);
            results.add(Thread.currentThread().getName());
            try {
                results.add(InitialContext.doLookup("java:comp/env/executorRef"));
            } catch (NamingException x) {
                results.add(x);
            }
            System.out.println("< sum");
        };

        // The test logic requires that all of these completable futures run in order.
        // To guarantee this, ensure that at least one of the completable futures supplied to thenAcceptBoth* is the previous one.

        CompletableFuture<Integer> cf1 = ManagedCompletableFuture.supplyAsync(() -> 1, defaultManagedExecutor);

        CompletableFuture<Integer> cf2 = ManagedCompletableFuture.supplyAsync(() -> 2, noContextExecutor);

        CompletableFuture<Integer> cf5 = cf1
                        .thenAcceptBothAsync(cf2, action)
                        .thenApply((unused) -> 3)
                        .thenAcceptBothAsync(cf1, action, testThreads)
                        .thenApply((unused) -> 4)
                        .thenAcceptBoth(cf1, action)
                        .thenApply((unused) -> 5);

        CompletableFuture<Void> cf7 = cf2.thenAcceptBothAsync(cf5, action);

        String threadName;
        Object lookupResult;

        // thenAcceptBothAsync on default execution facility
        assertEquals(Integer.valueOf(3), results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS));
        assertNotNull(threadName = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS).toString());
        assertTrue(threadName, threadName.startsWith("Default Executor-thread-")); // must run on Liberty global thread pool
        assertNotSame(currentThreadName, threadName); // cannot be the servlet thread because operation is async
        assertNotNull(lookupResult = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS));
        if (lookupResult instanceof Throwable)
            throw new Exception((Throwable) lookupResult);
        assertEquals(defaultManagedExecutor, lookupResult);

        // thenAcceptBothAsync on unmanaged executor
        assertEquals(Integer.valueOf(4), results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS));
        assertNotNull(threadName = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS).toString());
        assertFalse(threadName, threadName.startsWith("Default Executor-thread-")); // must run async on unmanaged thread
        assertNotNull(lookupResult = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS));
        if (lookupResult instanceof NamingException)
            ; // pass
        else if (lookupResult instanceof Throwable)
            throw new Exception((Throwable) lookupResult);
        else
            fail("Unexpected result of lookup: " + lookupResult);

        // thenAcceptBoth on unmanaged thread or servlet thread (context should be applied from stage creation time)
        assertEquals(Integer.valueOf(5), results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS));
        assertNotNull(threadName = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS).toString());
        assertTrue(threadName, threadName.equals(currentThreadName) || !threadName.startsWith("Default Executor-thread-")); // could run on current thread if previous stage is complete, otherwise must run on unmanaged thread
        assertNotNull(lookupResult = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS));
        if (lookupResult instanceof Throwable)
            throw new Exception((Throwable) lookupResult);
        assertEquals(defaultManagedExecutor, lookupResult);

        // thenAcceptBothAsync (second occurrence) on default execution facility
        assertEquals(Integer.valueOf(7), results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS));
        assertNotNull(threadName = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS).toString());
        assertTrue(threadName, threadName.startsWith("Default Executor-thread-")); // must run on Liberty global thread pool
        assertNotSame(currentThreadName, threadName); // cannot be the servlet thread because operation is async
        assertNotNull(lookupResult = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS));
        if (lookupResult instanceof NamingException)
            ; // pass
        else if (lookupResult instanceof Throwable)
            throw new Exception((Throwable) lookupResult);
        else
            fail("Unexpected result of lookup: " + lookupResult);

        assertTrue(cf7.toString(), cf7 instanceof ManagedCompletableFuture);
        assertNull(cf7.get(TIMEOUT_NS, TimeUnit.NANOSECONDS));
    }