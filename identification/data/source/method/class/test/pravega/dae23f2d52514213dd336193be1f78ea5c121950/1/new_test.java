@Test
    public void testAddWithBadInput() throws Exception {
        final long badBatchId = 12345;
        final long badParentId = 56789;
        final String badParentName = "Foo_Parent";
        final String badBatchName = "Foo_Batch";

        @Cleanup
        TestContext context = new TestContext(DEFAULT_CONFIG);
        context.storage.create(context.segmentMetadata.getName(), TIMEOUT).join();
        context.storage.create(context.batchMetadata.getName(), TIMEOUT).join();
        context.segmentAggregator.initialize(TIMEOUT).join();
        context.batchAggregator.initialize(TIMEOUT).join();

        // Create 2 more segments that can be used to verify MergeBatchOperation.
        context.containerMetadata.mapStreamSegmentId(badParentName, badParentId);
        UpdateableSegmentMetadata badBatchMeta = context.containerMetadata.mapStreamSegmentId(badBatchName, badBatchId, badParentId);
        badBatchMeta.setDurableLogLength(0);
        badBatchMeta.setStorageLength(0);
        context.storage.create(badBatchMeta.getName(), TIMEOUT).join();

        // 1. MergeBatchOperation
        // 1a.Verify that MergeBatchOperation cannot be added to the batch segment.
        AssertExtensions.assertThrows(
                "add() allowed a MergeBatchOperation on the batch segment.",
                () -> context.batchAggregator.add(generateSimpleMergeBatch(BATCH_ID, context)),
                ex -> ex instanceof IllegalArgumentException);

        // 1b. Verify that MergeBatchOperation has the right parent.
        AssertExtensions.assertThrows(
                "add() allowed a MergeBatchOperation on the parent for a Batch that did not have it as a parent.",
                () -> context.batchAggregator.add(generateSimpleMergeBatch(badBatchId, context)),
                ex -> ex instanceof IllegalArgumentException);

        // 2. StreamSegmentSealOperation.
        // 2a. Verify we cannot add a StreamSegmentSealOperation if the segment is not sealed yet.
        AssertExtensions.assertThrows(
                "add() allowed a StreamSegmentSealOperation for a non-sealed segment.",
                () -> {
                    @Cleanup
                    SegmentAggregator badBatchAggregator = new SegmentAggregator(badBatchMeta, context.dataSource, context.storage, DEFAULT_CONFIG, context.stopwatch);
                    badBatchAggregator.initialize(TIMEOUT).join();
                    badBatchAggregator.add(generateSimpleSeal(badBatchId, context));
                },
                ex -> ex instanceof DataCorruptionException);

        // 2b. Verify that nothing is allowed after Seal (after adding one append to and sealing the Batch Segment).
        StorageOperation batchAppend1 = generateAppendAndUpdateMetadata(0, BATCH_ID, context);
        context.batchAggregator.add(batchAppend1);
        context.batchAggregator.add(generateSealAndUpdateMetadata(BATCH_ID, context));
        AssertExtensions.assertThrows(
                "add() allowed operation after seal.",
                () -> context.batchAggregator.add(generateSimpleAppend(BATCH_ID, context)),
                ex -> ex instanceof DataCorruptionException);

        // 3. (Cached)StreamSegmentAppendOperation.
        // 3a. Add one append to the parent (nothing unusual here).
        StorageOperation parentAppend1 = generateAppendAndUpdateMetadata(0, SEGMENT_ID, context);
        context.segmentAggregator.add(parentAppend1);

        // 3b. Verify we cannot add anything beyond the DurableLogOffset (offset or offset+length).
        AssertExtensions.assertThrows(
                "add() allowed an operation beyond the DurableLogOffset (offset).",
                () -> {
                    // We have the correct offset, but we did not increase the DurableLogLength.
                    StreamSegmentAppendOperation badAppend = new StreamSegmentAppendOperation(context.segmentMetadata.getId(), "foo".getBytes(), APPEND_CONTEXT);
                    badAppend.setStreamSegmentOffset(parentAppend1.getStreamSegmentOffset() + parentAppend1.getLength());
                    context.segmentAggregator.add(badAppend);
                },
                ex -> ex instanceof DataCorruptionException);

        context.segmentMetadata.setDurableLogLength(parentAppend1.getStreamSegmentOffset() + parentAppend1.getLength() + 1);
        AssertExtensions.assertThrows(
                "add() allowed an operation beyond the DurableLogOffset (offset+length).",
                () -> {
                    // We have the correct offset, but we the append exceeds the DurableLogLength by 1 byte.
                    StreamSegmentAppendOperation badAppend = new StreamSegmentAppendOperation(context.segmentMetadata.getId(), "foo".getBytes(), APPEND_CONTEXT);
                    badAppend.setStreamSegmentOffset(parentAppend1.getStreamSegmentOffset() + parentAppend1.getLength());
                    context.segmentAggregator.add(badAppend);
                },
                ex -> ex instanceof DataCorruptionException);

        // 3c. Verify contiguity (offsets - we cannot have gaps in the data).
        AssertExtensions.assertThrows(
                "add() allowed an operation with wrong offset (too small).",
                () -> {
                    StreamSegmentAppendOperation badOffsetAppend = new StreamSegmentAppendOperation(context.segmentMetadata.getId(), "foo".getBytes(), APPEND_CONTEXT);
                    badOffsetAppend.setStreamSegmentOffset(0);
                    context.segmentAggregator.add(badOffsetAppend);
                },
                ex -> ex instanceof DataCorruptionException);

        AssertExtensions.assertThrows(
                "add() allowed an operation with wrong offset (too large).",
                () -> {
                    StreamSegmentAppendOperation badOffsetAppend = new StreamSegmentAppendOperation(context.segmentMetadata.getId(), "foo".getBytes(), APPEND_CONTEXT);
                    badOffsetAppend.setStreamSegmentOffset(parentAppend1.getStreamSegmentOffset() + parentAppend1.getLength() + 1);
                    context.segmentAggregator.add(badOffsetAppend);
                },
                ex -> ex instanceof DataCorruptionException);

        AssertExtensions.assertThrows(
                "add() allowed an operation with wrong offset (too large, but no pending operations).",
                () -> {
                    @Cleanup
                    SegmentAggregator badBatchAggregator = new SegmentAggregator(badBatchMeta, context.dataSource, context.storage, DEFAULT_CONFIG, context.stopwatch);
                    badBatchMeta.setDurableLogLength(100);
                    badBatchAggregator.initialize(TIMEOUT).join();

                    StreamSegmentAppendOperation badOffsetAppend = new StreamSegmentAppendOperation(context.segmentMetadata.getId(), "foo".getBytes(), APPEND_CONTEXT);
                    badOffsetAppend.setStreamSegmentOffset(1);
                    context.segmentAggregator.add(badOffsetAppend);
                },
                ex -> ex instanceof DataCorruptionException);

        // 4. Verify Segment Id match.
        AssertExtensions.assertThrows(
                "add() allowed an Append operation with wrong Segment Id.",
                () -> {
                    StreamSegmentAppendOperation badIdAppend = new StreamSegmentAppendOperation(Integer.MAX_VALUE, "foo".getBytes(), APPEND_CONTEXT);
                    badIdAppend.setStreamSegmentOffset(parentAppend1.getStreamSegmentOffset() + parentAppend1.getLength());
                    context.segmentAggregator.add(badIdAppend);
                },
                ex -> ex instanceof IllegalArgumentException);

        AssertExtensions.assertThrows(
                "add() allowed a StreamSegmentSealOperation with wrong SegmentId.",
                () -> {
                    StreamSegmentSealOperation badIdSeal = new StreamSegmentSealOperation(Integer.MAX_VALUE);
                    badIdSeal.setStreamSegmentOffset(parentAppend1.getStreamSegmentOffset() + parentAppend1.getLength());
                    context.segmentAggregator.add(badIdSeal);
                },
                ex -> ex instanceof IllegalArgumentException);

        AssertExtensions.assertThrows(
                "add() allowed a MergeBatchOperation with wrong SegmentId.",
                () -> {
                    MergeBatchOperation badIdMerge = new MergeBatchOperation(Integer.MAX_VALUE, context.batchMetadata.getId());
                    badIdMerge.setStreamSegmentOffset(parentAppend1.getStreamSegmentOffset() + parentAppend1.getLength());
                    badIdMerge.setLength(1);
                    context.segmentAggregator.add(badIdMerge);
                },
                ex -> ex instanceof IllegalArgumentException);
    }