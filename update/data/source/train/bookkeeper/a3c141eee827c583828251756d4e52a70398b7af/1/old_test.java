@Test
    public void testFlushSnapshot() throws IOException {
        HashSet<EntryKeyValue> keyValues = new HashSet<EntryKeyValue>();
        HashSet<EntryKeyValue> flushedKVs = new HashSet<EntryKeyValue>();
        KVFLusher flusher = new KVFLusher(flushedKVs);

        byte[] data = new byte[10];
        for (long entryId = 1; entryId < 100; entryId++) {
            for (long ledgerId = 1; ledgerId < 100; ledgerId++) {
                random.nextBytes(data);
                assertTrue(ledgerId + ":" + entryId + " is duplicate in mem-table!",
                        memTable.addEntry(ledgerId, entryId, ByteBuffer.wrap(data), this) != 0);
                assertTrue(ledgerId + ":" + entryId + " is duplicate in hash-set!",
                        keyValues.add(memTable.getEntry(ledgerId, entryId)));
                if (random.nextInt(16) == 0) {
                    if (null != memTable.snapshot()) {
                        if (random.nextInt(2) == 0) {
                            memTable.flush(flusher);
                        }
                    }
                }
            }
        }

        memTable.flush(flusher, Checkpoint.MAX);
        for (EntryKeyValue kv : keyValues) {
            assertTrue("kv " + kv.toString() + " was not flushed!", flushedKVs.contains(kv));
        }
    }