@Test
    public void testRemoveFinishedWrites() {
        final int timeIncrement = 1234 * 1000; // Just over 1ms.
        AtomicLong time = new AtomicLong();
        val q = new WriteQueue(MAX_PARALLELISM, time::get);

        val writes = new ArrayDeque<Write>();
        for (int i = 0; i < ITEM_COUNT; i++) {
            time.addAndGet(timeIncrement);
            val w = new Write(new ByteArraySegment(new byte[i]), new TestWriteLedger(i), new CompletableFuture<>());
            if (i % 2 == 0) {
                // Complete 1 out of two writes.
                w.complete(new TestLogAddress(i));
            }

            q.add(w);
            writes.addLast(w);
        }

        while (!writes.isEmpty()) {
            val write = writes.pollFirst();
            if (!write.isDone()) {
                boolean result1 = q.removeFinishedWrites();
                Assert.assertTrue("Unexpected value from removeFinishedWrites when there were writes left in the queue.", result1);
                val stats1 = q.getStatistics();
                Assert.assertEquals("Unexpected size after removeFinishedWrites with no effect.", writes.size() + 1, stats1.getSize());

                // Complete this write.
                write.complete(new TestLogAddress(time.get()));
            }

            // Estimate the Expected elapsed time based on the removals.
            long expectedElapsed = write.getTimestamp();
            int removed = 1;
            while (!writes.isEmpty() && writes.peekFirst().isDone()) {
                expectedElapsed += writes.pollFirst().getTimestamp();
                removed++;
            }
            expectedElapsed = (time.get() * removed - expectedElapsed) / AbstractTimer.NANOS_TO_MILLIS / removed;

            boolean result2 = q.removeFinishedWrites();
            Assert.assertEquals("Unexpected result from removeFinishedWrites.", !writes.isEmpty(), result2);
            val stats2 = q.getStatistics();
            Assert.assertEquals("Unexpected size after removeFinishedWrites.", writes.size(), stats2.getSize());
            Assert.assertEquals("Unexpected getExpectedProcessingTimeMillis after clear.", expectedElapsed, stats2.getExpectedProcessingTimeMillis());
        }
    }