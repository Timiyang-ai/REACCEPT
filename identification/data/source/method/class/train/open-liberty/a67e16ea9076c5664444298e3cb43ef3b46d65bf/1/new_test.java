@Test
    public void testCompletedStage() throws Exception {
        CompletionStage<Integer> cs0 = defaultManagedExecutor.completedStage(86);

        // Disallow CompletableFuture methods:
        CompletableFuture<Integer> cf0 = (CompletableFuture<Integer>) cs0;
        try {
            cf0.obtrudeValue(860);
            fail("obtrudeValue must not be permitted on minimal stage: ");
        } catch (UnsupportedOperationException x) {
        } // pass

        try {
            fail("cancel must not be permitted on minimal stage: " + cf0.cancel(true));
        } catch (UnsupportedOperationException x) {
        } // pass

        // Verify the value, and the thread of dependent stage:
        final CompletableFuture<String> cf = new CompletableFuture<String>();
        CompletionStage<Void> cs1 = cs0.thenAcceptAsync(value -> cf.complete(Thread.currentThread().getName() + ":" + value));

        // It's odd that the lambda supplied to cf.complete could run on the thread that invokes cf.get,
        // but that appears to happen infrequently, and it is Java's code, not OpenLiberty.  The test can
        // cope with it by polling for the cf to be done.
        for (long start = System.nanoTime(); !cf.isDone() && System.nanoTime() - start < TIMEOUT_NS; TimeUnit.MILLISECONDS.sleep(200));
        assertTrue(cf.isDone());

        String result = cf.getNow("value-if-absent");
        assertTrue(result, result.endsWith(":86"));
        assertTrue(result, result.startsWith("Default Executor-thread-"));
        assertTrue(result, !result.startsWith(Thread.currentThread().getName()));

        // Disallow CompletableFuture methods on dependent stage:
        CompletableFuture<Void> cf1 = (CompletableFuture<Void>) cs1;
        try {
            fail("get must not be permitted on minimal stage: " + cf1.get());
        } catch (UnsupportedOperationException x) {
        } // pass

        try {
            cf1.obtrudeException(new ArithmeticException("test"));
            fail("obtrudeException must not be permitted on minimal stage: ");
        } catch (UnsupportedOperationException x) {
        } // pass
    }