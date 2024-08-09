@Test
    @AllowedFFDC("java.lang.SecurityException")
    public void testAcceptEither() throws Exception {
        CountDownLatch blocker1 = new CountDownLatch(1);
        CountDownLatch blocker2 = new CountDownLatch(1);

        try {
            CompletableFuture<Boolean> cf1 = defaultManagedExecutor.supplyAsync(() -> {
                System.out.println("> supplyAsync[1] from testAcceptEither");
                try {
                    boolean result = blocker1.await(TIMEOUT_NS * 2, TimeUnit.NANOSECONDS);
                    System.out.println("< supplyAsync[1] " + result);
                    return result;
                } catch (InterruptedException x) {
                    System.out.println("< supplyAsync[1] " + x);
                    throw new CompletionException(x);
                }
            });

            CompletableFuture<Boolean> cf2 = CompletableFuture.supplyAsync(() -> {
                System.out.println("> supplyAsync[2] from testAcceptEither");
                try {
                    boolean result = blocker2.await(TIMEOUT_NS * 2, TimeUnit.NANOSECONDS);
                    System.out.println("< supplyAsync[2] " + result);
                    return result;
                } catch (InterruptedException x) {
                    System.out.println("< supplyAsync[2] " + x);
                    throw new CompletionException(x);
                }
            });

            LinkedBlockingQueue<Object> results = new LinkedBlockingQueue<Object>();
            CompletableFuture<Void> cf3 = cf1.acceptEither(cf2, (b) -> {
                System.out.println("> lookup from testAcceptEither");
                results.add(b);
                results.add(Thread.currentThread().getName());
                try {
                    ManagedExecutorService result = InitialContext.doLookup("java:module/noContextExecutorRef");
                    results.add(result);
                    System.out.println("< lookup: " + result);
                } catch (NamingException x) {
                    System.out.println("< lookup failed");
                    x.printStackTrace(System.out);
                    throw new CompletionException(x);
                }
            });

            assertFalse(cf3.isDone());
            try {
                Object result = cf3.get(100, TimeUnit.MILLISECONDS);
                fail("Dependent completion stage must not complete first: " + result);
            } catch (TimeoutException x) {
            }

            // Allow cf2 to complete
            blocker2.countDown();

            // Dependent stage must be able to complete now
            try {
                assertNull(cf3.get(TIMEOUT_NS, TimeUnit.NANOSECONDS));
            } catch (ExecutionException ee) {
                Throwable cause = ee.getCause();
                if (cause != null &&
                    cause instanceof java.lang.SecurityException &&
                    cause.getMessage() != null &&
                    cause.getMessage().contains("CWWKL0090E")) {
                    System.out.println("Caught an acceptable SecurityException from task running on un-managed thread");
                    return; // pass the test
                } else {
                    throw ee;
                }
            }
            assertTrue(cf3.isDone());
            assertFalse(cf3.isCancelled());
            assertFalse(cf3.isCompletedExceptionally());

            // Verify the parameter that is supplied to acceptEither's consumer
            assertEquals(Boolean.TRUE, results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS));

            // acceptEither runs on the unmanaged thread of the stage that completed
            String threadName;
            assertNotNull(threadName = (String) results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS));
            assertTrue(threadName, !threadName.startsWith(("Default Executor-thread-")));

            // thread context is made available to acceptEither's consumer per the managed executor which is the default asynchronous execution facility,
            // enabling java:module lookup to succeed from the unmanaged thread.
            Object lookupResult;
            assertNotNull(lookupResult = results.poll(TIMEOUT_NS, TimeUnit.NANOSECONDS));
            assertEquals(noContextExecutor, lookupResult);

            // allow cf1 to complete
            blocker1.countDown();
            assertEquals(Boolean.TRUE, cf1.get(TIMEOUT_NS, TimeUnit.NANOSECONDS));
        } finally {
            // allow threads to complete in case test fails
            blocker1.countDown();
            blocker2.countDown();
        }
    }