@Test
    public void testGetWritesToExecute() {
        final int ledgerChangeIndex = ITEM_COUNT - MAX_PARALLELISM / 2;
        val q = new WriteQueue(MAX_PARALLELISM);

        val writes = new ArrayList<Write>();
        int ledgerId = 0;
        for (int i = 0; i < ITEM_COUNT; i++) {
            if (i == ledgerChangeIndex) {
                ledgerId++;
            }

            val w = new Write(new ByteArraySegment(new byte[i]), new TestWriteLedger(ledgerId), new CompletableFuture<>());
            q.add(w);
            writes.add(w);
        }

        // 1. Throttled
        val throttledResult = q.getWritesToExecute(Long.MAX_VALUE);
        AssertExtensions.assertListEquals("Unexpected writes fetched with count throttling.",
                writes.subList(0, MAX_PARALLELISM), throttledResult, Object::equals);

        // 2. Max size reached.
        int sizeLimit = 10;
        val maxSizeResult = q.getWritesToExecute(sizeLimit);
        val expectedMaxSizeResult = new ArrayList<Write>();
        for (Write w : writes) {
            if (w.data.getLength() > sizeLimit) {
                break;
            }
            sizeLimit -= w.data.getLength();
            expectedMaxSizeResult.add(w);
        }

        AssertExtensions.assertListEquals("Unexpected writes fetched with size limit.",
                expectedMaxSizeResult, maxSizeResult, Object::equals);

        //3. Complete a few writes, then mark a few as in progress.
        writes.get(0).complete(new TestLogAddress(0));
        writes.get(1).beginAttempt();
        val result1 = q.getWritesToExecute(Long.MAX_VALUE);

        // We expect to skip over the first one and second one, but count the second one when doing throttling.
        AssertExtensions.assertListEquals("Unexpected writes fetched when some writes in progress (at beginning).",
                writes.subList(2, 1 + MAX_PARALLELISM), result1, Object::equals);

        //4. Mark a few writes as in progress after a non-progress write.
        writes.get(3).beginAttempt();
        val result2 = q.getWritesToExecute(Long.MAX_VALUE);
        Assert.assertEquals("Unexpected writes fetched when in-progress writes exist after non-in-progress writes.",
                0, result2.size());

        //5. LedgerChange.
        int beginIndex = ledgerChangeIndex - MAX_PARALLELISM / 2;
        for (int i = 0; i < beginIndex; i++) {
            writes.get(i).complete(new TestLogAddress(i));
        }

        q.removeFinishedWrites();
        val result3 = q.getWritesToExecute(Long.MAX_VALUE);
        AssertExtensions.assertListEquals("Unexpected writes fetched when ledger changed.",
                writes.subList(beginIndex, ledgerChangeIndex), result3, Object::equals);

        result3.forEach(w -> w.complete(new TestLogAddress(0)));
        q.removeFinishedWrites();
        val result4 = q.getWritesToExecute(Long.MAX_VALUE);
        AssertExtensions.assertListEquals("Unexpected writes fetched from the end, after ledger changed.",
                writes.subList(ledgerChangeIndex, writes.size()), result4, Object::equals);
    }