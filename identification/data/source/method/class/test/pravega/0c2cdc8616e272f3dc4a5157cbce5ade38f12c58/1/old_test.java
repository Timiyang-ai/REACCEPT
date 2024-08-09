@Test
    public void testClear() {
        val q = new WriteQueue(MAX_PARALLELISM);

        val expectedWrites = new ArrayList<Write>();
        for (int i = 0; i < ITEM_COUNT; i++) {
            val w = new Write(new ByteArraySegment(new byte[i]), new TestWriteLedger(i), CompletableFuture.completedFuture(null));
            q.add(w);
            expectedWrites.add(w);
        }

        val removedWrites = q.clear();
        AssertExtensions.assertListEquals("Unexpected writes removed.", expectedWrites, removedWrites, Object::equals);

        val clearStats = q.getStatistics();
        Assert.assertEquals("Unexpected getSize after clear.", 0, clearStats.getSize());
        Assert.assertEquals("Unexpected getAverageFillRate after clear.", 0, clearStats.getAverageItemFillRate(), 0);
        Assert.assertEquals("Unexpected getExpectedProcessingTimeMillis after clear.", 0, clearStats.getExpectedProcessingTimeMillis());
        Assert.assertEquals("Unexpected getMaxParallelism after clear.", MAX_PARALLELISM, clearStats.getMaxParallelism());

        q.add(new Write(new ByteArraySegment(new byte[BookKeeperConfig.MAX_APPEND_LENGTH]), new TestWriteLedger(0), CompletableFuture.completedFuture(null)));
        val addStats = q.getStatistics();
        Assert.assertEquals("Unexpected getSize after clear+add.", 1, addStats.getSize());
        Assert.assertEquals("Unexpected getAverageFillRate after clear+add.", 1, addStats.getAverageItemFillRate(), 0);
    }