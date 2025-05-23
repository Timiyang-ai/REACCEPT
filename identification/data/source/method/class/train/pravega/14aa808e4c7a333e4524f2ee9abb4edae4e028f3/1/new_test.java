@Test
    public void testGetWritesToExecute() {
        final int ledgerChangeIndex = ITEM_COUNT - 5;
        val q = new WriteQueue();
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

        // 1. Max size reached.
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

        //2. Complete a few writes, then mark a few as in progress.
        writes.get(0).setEntryId(0);
        writes.get(0).complete();
        writes.get(1).beginAttempt();
        val result1 = q.getWritesToExecute(Long.MAX_VALUE);

        // We expect to skip over the first one and second one, but count the second one when doing throttling.
        AssertExtensions.assertListEquals("Unexpected writes fetched when some writes in progress (at beginning).",
                writes.subList(2, ledgerChangeIndex), result1, Object::equals);

        //3. Mark a few writes as in progress after a non-progress write.
        writes.get(3).beginAttempt();
        val result2 = q.getWritesToExecute(Long.MAX_VALUE);
        Assert.assertEquals("Unexpected writes fetched when in-progress writes exist after non-in-progress writes.",
                0, result2.size());

        //4. LedgerChange.
        int beginIndex = ledgerChangeIndex - 5;
        for (int i = 0; i < beginIndex; i++) {
            writes.get(i).setEntryId(i);
            writes.get(i).complete();
        }

        q.removeFinishedWrites();
        val result3 = q.getWritesToExecute(Long.MAX_VALUE);
        AssertExtensions.assertListEquals("Unexpected writes fetched when ledger changed.",
                writes.subList(beginIndex, ledgerChangeIndex), result3, Object::equals);

        result3.forEach(w -> w.setEntryId(0));
        result3.forEach(Write::complete);
        q.removeFinishedWrites();
        val result4 = q.getWritesToExecute(Long.MAX_VALUE);
        AssertExtensions.assertListEquals("Unexpected writes fetched from the end, after ledger changed.",
                writes.subList(ledgerChangeIndex, writes.size()), result4, Object::equals);
    }