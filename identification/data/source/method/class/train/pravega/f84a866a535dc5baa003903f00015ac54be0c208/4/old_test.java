@Test
    public void testTruncate() throws Exception {
        try (DurableDataLog log = createDurableDataLog()) {
            // Check Read pre-initialization.
            AssertExtensions.assertThrows(
                    "truncate() worked before initialize()",
                    () -> log.truncate(createLogAddress(0), TIMEOUT),
                    ex -> ex instanceof IllegalStateException);

            log.initialize(TIMEOUT);
            TreeMap<LogAddress, byte[]> writeData = populate(log, getWriteCountForReads());
            ArrayList<LogAddress> addresses = new ArrayList<>(writeData.keySet());

            // Test truncating after each sequence number that we got back.
            for (LogAddress address : addresses) {
                boolean truncated = log.truncate(address, TIMEOUT).join();
                Assert.assertTrue("No truncation happened.", truncated);
                writeData.headMap(address, true).clear();
                verifyReads(log, createLogAddress(-1), writeData);
            }
        }
    }