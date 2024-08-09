@Test
    public void testAllOfWithResultsList() {
        int count = 10;

        // Already completed futures.
        List<CompletableFuture<Integer>> futures = createNumericFutures(count);
        completeFutures(futures);
        CompletableFuture<List<Integer>> allFuturesComplete = FutureHelpers.allOfWithResults(futures);
        Assert.assertTrue("allOfWithResults() did not create a completed future when all futures were previously complete.",
                allFuturesComplete.isDone() && !allFuturesComplete.isCompletedExceptionally());
        checkResults(allFuturesComplete.join());

        // Not completed futures.
        futures = createNumericFutures(count);
        allFuturesComplete = FutureHelpers.allOfWithResults(futures);
        Assert.assertFalse("allOfWithResults() created a completed future when none of the futures were previously complete.", allFuturesComplete.isDone());
        completeFutures(futures);
        Assert.assertTrue("The result of allOfWithResults() complete when all its futures completed.",
                allFuturesComplete.isDone() && !allFuturesComplete.isCompletedExceptionally());
        checkResults(allFuturesComplete.join());

        // At least one failed & completed future.
        futures = createNumericFutures(count);
        failRandomFuture(futures);
        allFuturesComplete = FutureHelpers.allOfWithResults(futures);
        Assert.assertFalse("allOfWithResults() created a completed future when not all of the futures were previously complete (but one failed).",
                allFuturesComplete.isDone());
        completeFutures(futures);
        Assert.assertTrue("The result of allOfWithResults() did not complete exceptionally when at least one of the futures failed.",
                allFuturesComplete.isCompletedExceptionally());

        // At least one failed future.
        futures = createNumericFutures(count);
        allFuturesComplete = FutureHelpers.allOfWithResults(futures);
        failRandomFuture(futures);
        Assert.assertFalse("The result of allOfWithResults() completed when not all the futures completed (except one that failed).",
                allFuturesComplete.isDone());
        completeFutures(futures);
        Assert.assertTrue("The result of allOfWithResults() did not complete exceptionally when at least one of the futures failed.",
                allFuturesComplete.isCompletedExceptionally());
    }