@Test
    public void testCompletedStage() throws Exception {
        // TODO switch to defaultManagedExecutor.completedStage once implemented
        CompletionStage<Integer> cs0;
        try {
            cs0 = ManagedCompletableFuture.completedStage(86);
        } catch (UnsupportedOperationException x) {
            if (AT_LEAST_JAVA_9)
                throw x;
            else
                return; // expected for Java SE 8
        }

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
        String result = cf.get(TIMEOUT_NS, TimeUnit.NANOSECONDS);
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