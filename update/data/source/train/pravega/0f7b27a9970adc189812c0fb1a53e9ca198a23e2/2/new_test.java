@Test
    public void testTruncate() throws Exception {
        try (DurableDataLog log = createDurableDataLog()) {
            // Check Read pre-initialization.
            AssertExtensions.assertThrows(
                    "truncate() worked before initialize()",
                    () -> log.truncate(new InMemoryDurableDataLog.InMemoryLogAddress(0), TIMEOUT),
                    ex -> ex instanceof IllegalStateException);

            log.initialize(TIMEOUT);
            TreeMap<Long, byte[]> writeData = populate(log, WRITE_COUNT);
            ArrayList<Long> seqNos = new ArrayList<>(writeData.keySet());

            // Test truncating after each sequence number that we got back.
            for (long seqNo : seqNos) {
                log.truncate(new InMemoryDurableDataLog.InMemoryLogAddress(seqNo), TIMEOUT).join();
                writeData.remove(seqNo);
                testRead(log, -1, writeData);
            }
        }
    }