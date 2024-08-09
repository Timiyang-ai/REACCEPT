@Test
    public void testReadDirect() throws Exception {
        final int randomAppendLength = 1024;

        @Cleanup
        TestContext context = new TestContext();
        ArrayList<Long> segmentIds = new ArrayList<>();
        final long segmentId = createSegment(0, context);
        final UpdateableSegmentMetadata segmentMetadata = context.metadata.getStreamSegmentMetadata(segmentId);
        segmentIds.add(segmentId);
        HashMap<Long, ArrayList<Long>> transactionsBySegment = createTransactions(segmentIds, 1, context);
        final long mergedTxId = transactionsBySegment.get(segmentId).get(0);

        // Add data to all segments.
        HashMap<Long, ByteArrayOutputStream> segmentContents = new HashMap<>();
        transactionsBySegment.values().forEach(segmentIds::addAll);
        appendData(segmentIds, segmentContents, context);

        // Mark everything so far (minus a few bytes) as being written to storage.
        segmentMetadata.setStorageLength(segmentMetadata.getLength() - 100);

        // Now partially merge a second transaction
        final long mergedTxOffset = beginMergeTransaction(mergedTxId, segmentMetadata, segmentContents, context);

        // Add one more append after all of this.
        final long endOfMergedDataOffset = segmentMetadata.getLength();
        byte[] appendData = new byte[randomAppendLength];
        new Random(0).nextBytes(appendData);
        appendSingleWrite(segmentId, appendData, context);
        recordAppend(segmentId, appendData, segmentContents);

        // At this point, in our (parent) segment:
        // * [0 .. StorageLength): no reads allowed.
        // * [StorageLength .. mergedTxOffset): should be fully available.
        // * [mergedTxOffset .. endOfMergedDataOffset): no reads allowed
        // * [endOfMergedDataOffset .. Length): should be fully available.

        // Verify we are not allowed to read from the range which has already been committed to Storage (invalid arguments).
        for (AtomicLong offset = new AtomicLong(0); offset.get() < segmentMetadata.getStorageLength(); offset.incrementAndGet()) {
            AssertExtensions.assertThrows(
                    String.format("readDirect allowed reading from an illegal offset (%s).", offset),
                    () -> context.readIndex.readDirect(segmentId, offset.get(), 1),
                    ex -> ex instanceof IllegalArgumentException);
        }

        // Verify that any reads overlapping a merged transaction return null (that is, we cannot retrieve the requested data).
        for (long offset = mergedTxOffset - 1; offset < endOfMergedDataOffset; offset++) {
            InputStream resultStream = context.readIndex.readDirect(segmentId, offset, 2);
            Assert.assertNull("readDirect() returned data overlapping a partially merged transaction", resultStream);
        }

        // Verify that we can read from any other offset.
        final byte[] expectedData = segmentContents.get(segmentId).toByteArray();
        BiConsumer<Long, Long> verifyReadResult = (startOffset, endOffset) -> {
            int readLength = (int) (endOffset - startOffset);
            while (readLength > 0) {
                InputStream actualDataStream;
                try {
                    actualDataStream = context.readIndex.readDirect(segmentId, startOffset, readLength);
                } catch (StreamSegmentNotExistsException ex) {
                    throw new CompletionException(ex);
                }
                Assert.assertNotNull(
                        String.format("Unexpected result when data is readily available for Offset = %s, Length = %s.", startOffset, readLength),
                        actualDataStream);

                byte[] actualData = new byte[readLength];
                try {
                    int bytesCopied = StreamHelpers.readAll(actualDataStream, actualData, 0, readLength);
                    Assert.assertEquals(
                            String.format("Unexpected number of bytes read for Offset = %s, Length = %s (pre-partial-merge).", startOffset, readLength),
                            readLength, bytesCopied);
                } catch (IOException ex) {
                    throw new UncheckedIOException(ex); // Technically not possible.
                }

                AssertExtensions.assertArrayEquals("Unexpected data read from the segment at offset " + startOffset,
                        expectedData, startOffset.intValue(), actualData, 0, actualData.length);

                // Setup the read for the next test (where we read 1 less byte than now).
                readLength--;
                if (readLength % 2 == 0) {
                    // For every 2 bytes of decreased read length, increase the start offset by 1. This allows for a greater
                    // number of combinations to be tested.
                    startOffset++;
                }
            }
        };

        // Verify that we can read the cached data just after the StorageLength but before the merged transaction.
        verifyReadResult.accept(segmentMetadata.getStorageLength(), mergedTxOffset);

        // Verify that we can read the cached data just after the merged transaction but before the end of the segment.
        verifyReadResult.accept(endOfMergedDataOffset, segmentMetadata.getLength());
    }