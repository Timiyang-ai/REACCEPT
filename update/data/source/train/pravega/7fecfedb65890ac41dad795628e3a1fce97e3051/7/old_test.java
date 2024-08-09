@Test
    public void testSeal() throws Exception {
        // Add some appends and seal, and then flush together. Verify that everything got flushed in one go.
        final int appendCount = 1000;
        final WriterConfig config = WriterConfig
                .builder()
                .with(WriterConfig.FLUSH_THRESHOLD_BYTES, appendCount * 50) // Extra high length threshold.
                .with(WriterConfig.FLUSH_THRESHOLD_MILLIS, 1000L)
                .with(WriterConfig.MAX_FLUSH_SIZE_BYTES, 10000)
                .with(WriterConfig.MIN_READ_TIMEOUT_MILLIS, 10L)
                .build();

        @Cleanup
        TestContext context = new TestContext(config);
        context.storage.create(context.segmentAggregator.getMetadata().getName(), TIMEOUT).join();
        context.segmentAggregator.initialize(TIMEOUT).join();

        @Cleanup
        ByteArrayOutputStream writtenData = new ByteArrayOutputStream();

        // Accumulate some Appends
        AtomicLong outstandingSize = new AtomicLong();
        SequenceNumberCalculator sequenceNumbers = new SequenceNumberCalculator(context, outstandingSize);
        for (int i = 0; i < appendCount; i++) {
            // Add another operation and record its length.
            StorageOperation appendOp = generateAppendAndUpdateMetadata(i, SEGMENT_ID, context);
            outstandingSize.addAndGet(appendOp.getLength());
            context.segmentAggregator.add(appendOp);
            getAppendData(appendOp, writtenData, context);
            sequenceNumbers.record(appendOp);

            // Call flush() and verify that we haven't flushed anything (by design).
            FlushResult flushResult = context.segmentAggregator.flush(TIMEOUT, executorService()).join();
            Assert.assertEquals(String.format("Not expecting a flush. OutstandingSize=%s, Threshold=%d", outstandingSize, config.getFlushThresholdBytes()),
                    0, flushResult.getFlushedBytes());
            Assert.assertEquals("Not expecting any merged bytes in this test.", 0, flushResult.getMergedBytes());
        }

        Assert.assertFalse("Unexpected value returned by mustFlush() before adding StreamSegmentSealOperation.", context.segmentAggregator.mustFlush());

        // Generate and add a Seal Operation.
        StorageOperation sealOp = generateSealAndUpdateMetadata(SEGMENT_ID, context);
        context.segmentAggregator.add(sealOp);
        Assert.assertEquals("Unexpected value returned by getLowestUncommittedSequenceNumber() after adding StreamSegmentSealOperation.", sequenceNumbers.getLowestUncommitted(), context.segmentAggregator.getLowestUncommittedSequenceNumber());
        Assert.assertTrue("Unexpected value returned by mustFlush() after adding StreamSegmentSealOperation.", context.segmentAggregator.mustFlush());

        // Call flush and verify that the entire Aggregator got flushed and the Seal got persisted to Storage.
        FlushResult flushResult = context.segmentAggregator.flush(TIMEOUT, executorService()).join();
        Assert.assertEquals("Expected the entire Aggregator to be flushed.", outstandingSize.get(), flushResult.getFlushedBytes());
        Assert.assertFalse("Unexpected value returned by mustFlush() after flushing.", context.segmentAggregator.mustFlush());
        Assert.assertEquals("Unexpected value returned by getLowestUncommittedSequenceNumber() after flushing.", Operation.NO_SEQUENCE_NUMBER, context.segmentAggregator.getLowestUncommittedSequenceNumber());

        // Verify data.
        byte[] expectedData = writtenData.toByteArray();
        byte[] actualData = new byte[expectedData.length];
        SegmentProperties storageInfo = context.storage.getStreamSegmentInfo(context.segmentAggregator.getMetadata().getName(), TIMEOUT).join();
        Assert.assertEquals("Unexpected number of bytes flushed to Storage.", expectedData.length, storageInfo.getLength());
        Assert.assertTrue("Segment is not sealed in storage post flush.", storageInfo.isSealed());
        Assert.assertTrue("Segment is not marked in metadata as sealed in storage post flush.", context.segmentAggregator.getMetadata().isSealedInStorage());
        context.storage.read(context.segmentAggregator.getMetadata().getName(), 0, actualData, 0, actualData.length, TIMEOUT).join();

        Assert.assertArrayEquals("Unexpected data written to storage.", expectedData, actualData);
    }