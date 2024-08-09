@Test(dependsOnMethods = "testWatchDelete")
    public void testCancelWatch() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Boolean> future = watcher.cancel();
        test.assertTrue(future.get(5, TimeUnit.SECONDS));
    }