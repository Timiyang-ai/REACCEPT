@Test
    public void testExceptionally() throws Exception {
        AtomicInteger count = new AtomicInteger();
        LinkedBlockingQueue<Object> results = new LinkedBlockingQueue<Object>();
        String currentThreadName = Thread.currentThread().getName();

        final Function<Throwable, Executor> lookup = (previousFailure) -> {
            System.out.println("> lookup #" + count.incrementAndGet() + " from testExceptionally");
            if (previousFailure != null)
                results.add(previousFailure);
            results.add(Thread.currentThread().getName());
            try {
                ManagedExecutorService result = InitialContext.doLookup("java:comp/env/executorRef");
                System.out.println("< lookup: " + result);
                return result;
            } catch (NamingException x) {
                System.out.println("< lookup failed");
                x.printStackTrace(System.out);
                throw new CompletionException(x);
            }
        };

        String threadName;
        Object previousFailure;

        // Verify that exceptionally is skipped when no exception is raised by prior stage

        CompletableFuture<?> cf1 = ManagedCompletableFuture
                        .completedFuture((Throwable) null)
                        .thenApplyAsync(lookup) // expect lookup to succeed because managed executor transfers thread context from the servlet
                        .exceptionally(lookup); // should not be invoked due to lack of any failure in prior stage

        assertTrue(cf1.toString(), cf1 instanceof ManagedCompletableFuture);

        // thenApplyAsync on default execution facility
        assertNotNull(threadName = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS).toString());
        assertTrue(threadName, threadName.startsWith("Default Executor-thread-")); // must run on Liberty global thread pool
        assertNotSame(currentThreadName, threadName); // cannot be the servlet thread because operation is async

        assertEquals(defaultManagedExecutor, cf1.get(TIMEOUT_NS, TimeUnit.NANOSECONDS));

        assertEquals(1, count.get()); // lookup function only ran once

        // Verify that exceptionally is invoked when exception is raised by prior stage

        CompletableFuture<?> cf2 = ManagedCompletableFuture
                        .completedFuture((Throwable) null)
                        .thenApplyAsync(lookup, testThreads) // expect lookup to fail without the context of the servlet thread
                        .exceptionally(lookup);

        assertTrue(cf2.toString(), cf2 instanceof ManagedCompletableFuture);

        // thenApplyAsync on unmanaged executor
        assertNotNull(threadName = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS).toString());
        assertFalse(threadName, threadName.startsWith("Default Executor-thread-")); // must run async on unmanaged thread

        // exceptionally on unmanaged thread or servlet thread
        assertNotNull(previousFailure = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS));
        if (previousFailure instanceof CompletionException && ((CompletionException) previousFailure).getCause() instanceof NamingException)
            ; // pass
        else if (previousFailure instanceof Throwable)
            throw new Exception((Throwable) previousFailure);
        else
            fail("Unexpected value supplied to function as previous failure: " + previousFailure);

        String previousThreadName = threadName;
        assertNotNull(threadName = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS).toString());
        assertTrue(threadName, previousThreadName.equals(threadName) || currentThreadName.equals(threadName)); // must run on same unmanaged thread or on servlet thread

        assertEquals(defaultManagedExecutor, cf2.get(TIMEOUT_NS, TimeUnit.NANOSECONDS));
        assertEquals(3, count.get()); // two additional executions of the lookup function
    }