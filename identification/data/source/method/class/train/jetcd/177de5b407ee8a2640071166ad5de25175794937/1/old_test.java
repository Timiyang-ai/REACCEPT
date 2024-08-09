@Test(dependsOnMethods = "testWatchDelete")
    public void testCancelWatch() throws ExecutionException, InterruptedException, TimeoutException {
        watchClient.cancelWatch(watcher);
        WatchResponse watchResponse = cancelResponse.get(5, TimeUnit.SECONDS);
        test.assertTrue(watchResponse.getCanceled());
    }