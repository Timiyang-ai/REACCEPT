@Test
    public void testAppend() throws Exception {
        try (DurableDataLog log = createDurableDataLog()) {
            // Check Append pre-initialization.
            AssertExtensions.assertThrows(
                    "append() worked before initialize()",
                    () -> log.append(new ByteArrayInputStream("h".getBytes()), TIMEOUT),
                    ex -> ex instanceof IllegalStateException);

            log.initialize(TIMEOUT);

            // Only verify sequence number monotonicity. We'll verify reads in its own test.
            LogAddress prevAddress = null;
            int writeCount = getWriteCountForWrites();
            for (int i = 0; i < writeCount; i++) {
                LogAddress address = log.append(new ByteArrayInputStream(String.format("Write_%s", i).getBytes()), TIMEOUT).join();
                Assert.assertNotNull("No address returned from append().", address);
                if (prevAddress != null) {
                    AssertExtensions.assertGreaterThan("Sequence Number is not monotonically increasing.", prevAddress.getSequence(), address.getSequence());
                }

                prevAddress = address;
            }
        }
    }