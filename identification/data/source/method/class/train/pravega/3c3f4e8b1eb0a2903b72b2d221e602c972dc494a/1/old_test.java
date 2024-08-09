@Test
    public void testRemoveFinishedWrites() {
        final int timeIncrement = 1234 * 1000; // Just over 1ms.
        AtomicLong time = new AtomicLong();
        val q = new WriteQueue(time::get);
        val writes = new ArrayDeque<Write>();
        for (int i = 0; i < ITEM_COUNT; i++) {
            time.addAndGet(timeIncrement);
            val w = new Write(new ByteArraySegment(new byte[i]), new TestWriteLedger(i), new CompletableFuture<>());
            if (i % 2 == 0) {
                // Complete 1 out of two writes.
                w.setEntryId(i);
                w.complete();
            }

            q.add(w);
            writes.addLast(w);
        }

        while (!writes.isEmpty()) {
            val write = writes.pollFirst();
            if (!write.isDone()) {
                val result1 = q.removeFinishedWrites();
                AssertExtensions.assertContainsSameElements("Unexpected value from removeFinishedWrites when there were writes left in the queue.",
                        EnumSet.of(WriteQueue.CleanupStatus.QueueNotEmpty), result1);
                val stats1 = q.getStatistics();
                Assert.assertEquals("Unexpected size after removeFinishedWrites with no effect.", writes.size() + 1, stats1.getSize());

                // Complete this write.
                write.setEntryId(time.get());
                write.complete();
            }

            // Estimate the Expected elapsed time based on the removals.
            long expectedElapsed = write.getQueueAddedTimestamp();
            int removed = 1;
            while (!writes.isEmpty() && writes.peekFirst().isDone()) {
                expectedElapsed += writes.pollFirst().getQueueAddedTimestamp();
                removed++;
            }
            expectedElapsed = (time.get() * removed - expectedElapsed) / AbstractTimer.NANOS_TO_MILLIS / removed;

            val result2 = q.removeFinishedWrites();
            val expectedResult = EnumSet.of(writes.isEmpty() ? WriteQueue.CleanupStatus.QueueEmpty : WriteQueue.CleanupStatus.QueueNotEmpty);
            AssertExtensions.assertContainsSameElements("Unexpected result from removeFinishedWrites.", expectedResult, result2);
            val stats2 = q.getStatistics();
            Assert.assertEquals("Unexpected size after removeFinishedWrites.", writes.size(), stats2.getSize());
            Assert.assertEquals("Unexpected getExpectedProcessingTimeMillis after clear.", expectedElapsed, stats2.getExpectedProcessingTimeMillis());
        }

        // Verify that it does report failed writes when encountered.
        val w3 = new Write(new ByteArraySegment(new byte[1]), new TestWriteLedger(0), new CompletableFuture<>());
        q.add(w3);
        w3.fail(new IntentionalException(), true);
        val result3 = q.removeFinishedWrites();
        AssertExtensions.assertContainsSameElements("Unexpected value from removeFinishedWrites when there were failed writes.",
                EnumSet.of(WriteQueue.CleanupStatus.QueueEmpty, WriteQueue.CleanupStatus.WriteFailed), result3);

    }