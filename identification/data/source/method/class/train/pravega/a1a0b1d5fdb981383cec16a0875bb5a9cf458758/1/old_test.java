@Test
    public void testGetOrAssignStreamSegmentId() {
        final int segmentCount = 10;
        final int batchesPerSegment = 5;

        HashSet<String> storageSegments = new HashSet<>();
        for (int i = 0; i < segmentCount; i++) {
            String segmentName = getName(i);
            storageSegments.add(segmentName);
            for (int j = 0; j < batchesPerSegment; j++) {
                // There is a small chance of a name conflict here, but we don't care. As long as we get at least one
                // batch per segment, we should be fine.
                String batchName = StreamSegmentNameUtils.generateBatchStreamSegmentName(segmentName);
                storageSegments.add(batchName);
            }
        }

        // We setup all necessary handlers, except the one for create. We do not need to create new Segments here.
        @Cleanup
        TestContext context = new TestContext();
        setupOperationLog(context);
        Predicate<String> isSealed = segmentName -> segmentName.hashCode() % 2 == 0;
        Function<String, Long> getInitialLength = segmentName -> (long) Math.abs(segmentName.hashCode());
        setupStorageGetHandler(context, storageSegments, segmentName -> new StreamSegmentInformation(segmentName, getInitialLength.apply(segmentName), isSealed.test(segmentName), false, new Date()));

        // First, map all the parents (stand-alone segments).
        for (String name : storageSegments) {
            if (StreamSegmentNameUtils.getParentStreamSegmentName(name) == null) {
                long id = context.mapper.getOrAssignStreamSegmentId(name, TIMEOUT).join();
                Assert.assertNotEquals("No id was assigned for StreamSegment " + name, SegmentMetadataCollection.NO_STREAM_SEGMENT_ID, id);
                SegmentMetadata sm = context.metadata.getStreamSegmentMetadata(id);
                Assert.assertNotNull("No metadata was created for StreamSegment " + name, sm);
                long expectedLength = getInitialLength.apply(name);
                boolean expectedSeal = isSealed.test(name);
                Assert.assertEquals("Metadata does not have the expected length for StreamSegment " + name, expectedLength, sm.getDurableLogLength());
                Assert.assertEquals("Metadata does not have the expected value for isSealed for StreamSegment " + name, expectedSeal, sm.isSealed());
            }
        }

        // Now, map all the parents (stand-alone segments).
        for (String name : storageSegments) {
            String parentName = StreamSegmentNameUtils.getParentStreamSegmentName(name);
            if (parentName != null) {
                long id = context.mapper.getOrAssignStreamSegmentId(name, TIMEOUT).join();
                Assert.assertNotEquals("No id was assigned for Batch " + name, SegmentMetadataCollection.NO_STREAM_SEGMENT_ID, id);
                SegmentMetadata sm = context.metadata.getStreamSegmentMetadata(id);
                Assert.assertNotNull("No metadata was created for Batch " + name, sm);
                long expectedLength = getInitialLength.apply(name);
                boolean expectedSeal = isSealed.test(name);
                Assert.assertEquals("Metadata does not have the expected length for Batch " + name, expectedLength, sm.getDurableLogLength());
                Assert.assertEquals("Metadata does not have the expected value for isSealed for Batch " + name, expectedSeal, sm.isSealed());

                // Check parenthood.
                Assert.assertNotEquals("No parent defined in metadata for Batch " + name, SegmentMetadataCollection.NO_STREAM_SEGMENT_ID, sm.getParentId());
                long parentId = context.metadata.getStreamSegmentId(parentName);
                Assert.assertEquals("Unexpected parent defined in metadata for Batch " + name, parentId, sm.getParentId());
            }
        }
    }