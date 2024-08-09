@Test
    public void testThenCombine() throws Exception {
        LinkedBlockingQueue<Object> results = new LinkedBlockingQueue<Object>();
        String currentThreadName = Thread.currentThread().getName();

        BiFunction<Integer, Integer, Integer> sum = (a, b) -> {
            System.out.println("> sum " + a + "+" + b + " from testThenCombine");
            results.add(Thread.currentThread().getName());
            try {
                results.add(InitialContext.doLookup("java:comp/env/executorRef"));
            } catch (NamingException x) {
                results.add(x);
            }
            System.out.println("< sum: " + (a + b));
            return a + b;
        };

        // The test logic requires that all of these completable futures run in order.
        // To guarantee this, ensure that at least one of the completable futures supplied to thenCombine* is the previous one.

        CompletableFuture<Integer> cf1 = ManagedCompletableFuture.supplyAsync(() -> 1, defaultManagedExecutor);

        CompletableFuture<Integer> cf2 = ManagedCompletableFuture.supplyAsync(() -> 2, noContextExecutor);

        CompletableFuture<Integer> cf3 = cf1.thenCombineAsync(cf2, sum);

        CompletableFuture<Integer> cf4 = cf1.thenCombineAsync(cf3, sum, testThreads);

        CompletableFuture<Integer> cf5 = cf4.thenCombine(cf1, sum);

        CompletableFuture<Integer> cf6 = cf5.thenCombineAsync(cf1, sum);

        // Submit from thread that lacks context
        CompletableFuture<CompletableFuture<Integer>> cfcf12 = CompletableFuture.supplyAsync(() -> cf6.thenCombineAsync(cf6, sum));

        String threadName;
        Object lookupResult;

        assertTrue(cf1.toString(), cf1 instanceof ManagedCompletableFuture);
        assertTrue(cf2.toString(), cf2 instanceof ManagedCompletableFuture);
        assertTrue(cf3.toString(), cf3 instanceof ManagedCompletableFuture);
        assertTrue(cf4.toString(), cf4 instanceof ManagedCompletableFuture);
        assertTrue(cf5.toString(), cf5 instanceof ManagedCompletableFuture);
        assertTrue(cf6.toString(), cf6 instanceof ManagedCompletableFuture);

        // thenCombineAsync on default execution facility
        assertNotNull(threadName = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS).toString());
        assertTrue(threadName, threadName.startsWith("Default Executor-thread-")); // must run on Liberty global thread pool
        assertNotSame(currentThreadName, threadName); // cannot be the servlet thread because operation is async
        assertNotNull(lookupResult = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS));
        if (lookupResult instanceof Throwable)
            throw new Exception((Throwable) lookupResult);
        assertEquals(defaultManagedExecutor, lookupResult);
        assertEquals(Integer.valueOf(3), cf3.get(TIMEOUT_NS, TimeUnit.NANOSECONDS));

        // thenCombineAsync on unmanaged executor
        assertNotNull(threadName = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS).toString());
        assertFalse(threadName, threadName.startsWith("Default Executor-thread-")); // must run async on unmanaged thread
        assertNotNull(lookupResult = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS));
        if (lookupResult instanceof NamingException)
            ; // pass
        else if (lookupResult instanceof Throwable)
            throw new Exception((Throwable) lookupResult);
        else
            fail("Unexpected result of lookup: " + lookupResult);
        assertEquals(Integer.valueOf(4), cf4.get(TIMEOUT_NS, TimeUnit.NANOSECONDS));

        // thenCombine on unmanaged thread or servlet thread (context should be applied from stage creation time)
        assertNotNull(threadName = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS).toString());
        assertTrue(threadName, threadName.equals(currentThreadName) || !threadName.startsWith("Default Executor-thread-")); // could run on current thread if previous stage is complete, otherwise must run on unmanaged thread
        assertNotNull(lookupResult = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS));
        if (lookupResult instanceof Throwable)
            throw new Exception((Throwable) lookupResult);
        assertEquals(defaultManagedExecutor, lookupResult);
        assertEquals(Integer.valueOf(5), cf5.get(TIMEOUT_NS, TimeUnit.NANOSECONDS));

        // thenCombineAsync (second occurrence) on default execution facility
        assertNotNull(threadName = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS).toString());
        assertTrue(threadName, threadName.startsWith("Default Executor-thread-")); // must run on Liberty global thread pool
        assertNotSame(currentThreadName, threadName); // cannot be the servlet thread because operation is async
        assertNotNull(lookupResult = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS));
        if (lookupResult instanceof Throwable)
            throw new Exception((Throwable) lookupResult);
        assertEquals(defaultManagedExecutor, lookupResult);
        assertEquals(Integer.valueOf(6), cf6.get(TIMEOUT_NS, TimeUnit.NANOSECONDS));

        // thenCombineAsync requested from unmanaged thread
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

        CompletableFuture<Integer> cf12 = cfcf12.get(TIMEOUT_NS, TimeUnit.NANOSECONDS);
        assertEquals(Integer.valueOf(12), cf12.get(TIMEOUT_NS, TimeUnit.NANOSECONDS));
    }