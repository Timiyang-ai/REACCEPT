@Test
    public void testAppend() {
        try (DurableDataLog log = createDurableDataLog()) {
            // Check Append pre-initialization.
            AssertExtensions.assertThrows(
                    "append() worked before initialize()",
                    () -> log.append(new ByteArrayInputStream("h".getBytes()), Timeout),
                    ex -> ex instanceof IllegalStateException);

            log.initialize(Timeout).join();

            // Only verify sequence number monotonicity. We'll verify reads in its own test.
            long prevSeqNo = -1;
            for (int i = 0; i < writeCount; i++) {
                byte[] writeData = String.format("Write_%s", i).getBytes();
                ByteArrayInputStream writeStream = new ByteArrayInputStream(writeData);
                long seqNo = log.append(writeStream, Timeout).join();

                AssertExtensions.assertGreaterThan("Sequence Number is not monotonically increasing.", prevSeqNo, seqNo);
                prevSeqNo = seqNo;
            }
        }
    }