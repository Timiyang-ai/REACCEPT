@Test
    public void testGetStreamSegmentInfo() {
        final String segmentName = "segment";
        final long segmentId = 1;

        @Cleanup
        TestContext context = new TestContext();
        HashSet<String> storageSegments = new HashSet<>();

        // Segment not exists in Metadata or Storage.
        setupStorageGetHandler(context, storageSegments, sn -> {
            throw new CompletionException(new StreamSegmentNotExistsException(sn));
        });
        setSavedState(segmentName, segmentId, 0, ATTRIBUTE_COUNT, context);
        val segmentState = context.stateStore.get(segmentName, TIMEOUT).join();
        Map<UUID, Long> expectedAttributes = segmentState == null ? null : segmentState.getAttributes();

        AssertExtensions.assertThrows(
                "getStreamSegmentInfo did not throw correct exception when segment does not exist in Metadata or Storage.",
                () -> context.mapper.getStreamSegmentInfo(segmentName, TIMEOUT),
                ex -> ex instanceof StreamSegmentNotExistsException);

        // Segment does not exist in Metadata, but does so in Storage.
        // Since we do not setup an OperationLog, we guarantee that there is no attempt to map this in the metadata.
        val segmentInfo = StreamSegmentInformation.builder()
                                                  .name(segmentName)
                                                  .length(123)
                                                  .sealed(true)
                                                  .build();
        storageSegments.add(segmentName);
        setupStorageGetHandler(context, storageSegments, sn -> segmentInfo);
        val inStorageInfo = context.mapper.getStreamSegmentInfo(segmentName, TIMEOUT).join();
        assertEquals("Unexpected SegmentInfo when Segment exists in Storage.", segmentInfo, inStorageInfo);
        SegmentMetadataComparer.assertSameAttributes("Unexpected attributes when Segment exists in Storage", expectedAttributes, inStorageInfo);
        Assert.assertEquals("Not expecting any segments to be mapped.", 0, context.metadata.getAllStreamSegmentIds().size());

        // Segment exists in Metadata (and in Storage too) - here, we set different values in the Metadata to verify that
        // the info is fetched from there.
        val sm = context.metadata.mapStreamSegmentId(segmentName, segmentId);
        sm.setLength(segmentInfo.getLength() + 1);
        sm.updateAttributes(Collections.singletonMap(UUID.randomUUID(), 12345L));
        val inMetadataInfo = context.mapper.getStreamSegmentInfo(segmentName, TIMEOUT).join();
        assertEquals("Unexpected SegmentInfo when Segment exists in Metadata.", sm, inMetadataInfo);
        SegmentMetadataComparer.assertSameAttributes("Unexpected attributes when Segment exists in Metadata.",
                sm.getAttributes(), inMetadataInfo);

        // Segment exists in Metadata, but is marked as deleted.
        sm.markDeleted();
        AssertExtensions.assertThrows(
                "getStreamSegmentInfo did not throw correct exception when segment is marked as Deleted in metadata.",
                () -> context.mapper.getStreamSegmentInfo(segmentName, TIMEOUT),
                ex -> ex instanceof StreamSegmentNotExistsException);
    }