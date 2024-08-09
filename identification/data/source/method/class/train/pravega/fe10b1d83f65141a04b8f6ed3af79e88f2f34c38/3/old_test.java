@Test
    public void testGetOrAssignStreamSegmentId() {
        final long minSegmentLength = 1;
        final int segmentCount = 10;
        final int transactionsPerSegment = 5;
        final long noSegmentId = ContainerMetadata.NO_STREAM_SEGMENT_ID;
        AtomicLong currentSegmentId = new AtomicLong(Integer.MAX_VALUE);
        Supplier<Long> nextSegmentId = () -> currentSegmentId.decrementAndGet() % 2 == 0 ? noSegmentId : currentSegmentId.get();
        Function<String, Long> getSegmentLength = segmentName -> minSegmentLength + (long) MathHelpers.abs(segmentName.hashCode());
        Function<String, Long> getSegmentStartOffset = segmentName -> getSegmentLength.apply(segmentName) / 2;

        @Cleanup
        TestContext context = new TestContext();

        HashSet<String> storageSegments = new HashSet<>();
        for (int i = 0; i < segmentCount; i++) {
            String segmentName = getName(i);
            storageSegments.add(segmentName);
            setSavedState(segmentName, nextSegmentId.get(), getSegmentStartOffset.apply(segmentName), storageSegments.size() % ATTRIBUTE_COUNT, context);

            for (int j = 0; j < transactionsPerSegment; j++) {
                // There is a small chance of a name conflict here, but we don't care. As long as we get at least one
                // Transaction per segment, we should be fine.
                String transactionName = StreamSegmentNameUtils.getTransactionNameFromId(segmentName, UUID.randomUUID());
                storageSegments.add(transactionName);
                setSavedState(transactionName, nextSegmentId.get(), getSegmentStartOffset.apply(transactionName),
                        storageSegments.size() % ATTRIBUTE_COUNT, context);
            }
        }

        // We setup all necessary handlers, except the one for create. We do not need to create new Segments here.
        setupOperationLog(context);
        Predicate<String> isSealed = segmentName -> segmentName.hashCode() % 2 == 0;
        setupStorageGetHandler(context, storageSegments, segmentName ->
                StreamSegmentInformation.builder()
                                        .name(segmentName)
                                        .length(getSegmentLength.apply(segmentName))
                                        .sealed(isSealed.test(segmentName))
                                        .build());

        // First, map all the parents (stand-alone segments).
        for (String name : storageSegments) {
            if (StreamSegmentNameUtils.getParentStreamSegmentName(name) == null) {
                long id = context.mapper.getOrAssignStreamSegmentId(name, TIMEOUT).join();
                Assert.assertNotEquals("No id was assigned for StreamSegment " + name, ContainerMetadata.NO_STREAM_SEGMENT_ID, id);
                SegmentMetadata sm = context.metadata.getStreamSegmentMetadata(id);
                Assert.assertNotNull("No metadata was created for StreamSegment " + name, sm);
                long expectedLength = getSegmentLength.apply(name);
                boolean expectedSeal = isSealed.test(name);
                Assert.assertEquals("Metadata does not have the expected length for StreamSegment " + name, expectedLength, sm.getLength());
                Assert.assertEquals("Metadata does not have the expected value for isSealed for StreamSegment " + name, expectedSeal, sm.isSealed());

                val segmentState = context.stateStore.get(name, TIMEOUT).join();
                Map<UUID, Long> expectedAttributes = segmentState == null ? null : segmentState.getAttributes();
                SegmentMetadataComparer.assertSameAttributes("Unexpected attributes in metadata for StreamSegment " + name, expectedAttributes, sm);
                long expectedStartOffset = segmentState == null ? 0 : segmentState.getStartOffset();
                Assert.assertEquals("Unexpected StartOffset in metadata for " + name, expectedStartOffset, sm.getStartOffset());
            }
        }

        // Now, map all the Transactions.
        for (String name : storageSegments) {
            String parentName = StreamSegmentNameUtils.getParentStreamSegmentName(name);
            if (parentName != null) {
                long id = context.mapper.getOrAssignStreamSegmentId(name, TIMEOUT).join();
                Assert.assertNotEquals("No id was assigned for Transaction " + name, ContainerMetadata.NO_STREAM_SEGMENT_ID, id);
                SegmentMetadata sm = context.metadata.getStreamSegmentMetadata(id);
                Assert.assertNotNull("No metadata was created for Transaction " + name, sm);
                long expectedLength = getSegmentLength.apply(name);
                boolean expectedSeal = isSealed.test(name);
                Assert.assertEquals("Metadata does not have the expected length for Transaction " + name, expectedLength, sm.getLength());
                Assert.assertEquals("Metadata does not have the expected value for isSealed for Transaction " + name, expectedSeal, sm.isSealed());

                val segmentState = context.stateStore.get(name, TIMEOUT).join();
                Map<UUID, Long> expectedAttributes = segmentState == null ? null : segmentState.getAttributes();
                SegmentMetadataComparer.assertSameAttributes("Unexpected attributes in metadata for Transaction " + name, expectedAttributes, sm);

                // For transactions we do not expect to see any non-zero start offsets.
                Assert.assertEquals("Unexpected StartOffset in metadata for " + name, 0, sm.getStartOffset());

                // Check parenthood.
                Assert.assertNotEquals("No parent defined in metadata for Transaction " + name, ContainerMetadata.NO_STREAM_SEGMENT_ID, sm.getParentId());
                long parentId = context.metadata.getStreamSegmentId(parentName, false);
                Assert.assertEquals("Unexpected parent defined in metadata for Transaction " + name, parentId, sm.getParentId());
            }
        }
    }