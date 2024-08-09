@Test(timeout = TIMEOUT_MILLIS)
    public void testAppend() throws Exception {
        try (DurableDataLog log = createDurableDataLog()) {
            // Check Append pre-initialization.
            AssertExtensions.assertThrows(
                    "append() worked before initialize()",
                    () -> log.append(new ByteArraySegment("h".getBytes()), TIMEOUT),
                    ex -> ex instanceof IllegalStateException);

            log.initialize(TIMEOUT);

            // Only verify sequence number monotonicity. We'll verify reads in its own test.
            LogAddress prevAddress = null;
            int writeCount = getWriteCount();
            for (int i = 0; i < writeCount; i++) {
                LogAddress address = log.append(new ByteArraySegment(getWriteData()), TIMEOUT).join();
                Assert.assertNotNull("No address returned from append().", address);
                if (prevAddress != null) {
                    AssertExtensions.assertGreaterThan("Sequence Number is not monotonically increasing.", prevAddress.getSequence(), address.getSequence());
                }

                prevAddress = address;
            }
        }
    }