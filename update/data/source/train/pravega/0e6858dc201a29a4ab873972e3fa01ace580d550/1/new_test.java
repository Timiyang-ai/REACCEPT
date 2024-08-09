@Test
    public void testClose() throws Exception {
        BlockingDrainingQueue<Integer> queue = new BlockingDrainingQueue<>();
        AtomicReference<List<Integer>> result = new AtomicReference<>();
        CompletableFuture<Void> resultSet = new CompletableFuture<>();
        Thread t = new Thread(() -> {
            try {
                result.set(queue.takeAllEntries());
                resultSet.complete(null);
            } catch (Exception ex) {
                resultSet.completeExceptionally(ex);
            }
        });

        t.start();

        // Verify the queue hasn't returned before we actually set the result.
        Assert.assertNull("Queue unblocked before result was set.", result.get());
        Thread.sleep(10);
        List<Integer> queueContents = queue.close();

        // Verify result.
        AssertExtensions.assertThrows(
                "Future was not cancelled with the correct exception.",
                resultSet::join,
                ex -> ex instanceof InterruptedException);

        Assert.assertNull("Queue returned an item even if it got closed.", result.get());
        Assert.assertEquals("Queue.close() returned an item even though it was empty.", 0, queueContents.size());
    }