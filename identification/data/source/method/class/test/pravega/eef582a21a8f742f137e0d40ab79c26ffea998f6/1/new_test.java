@Test
    public void testGetStreamSegmentInfo() {
        @Cleanup
        val context = new TestContext();
        context.container.startAsync().awaitRunning();

        // Non-existent segment.
        AssertExtensions.assertSuppliedFutureThrows(
                "Unexpected exception when the segment does not exist.",
                () -> context.container.getStreamSegmentInfo(SEGMENT_NAME, TIMEOUT),
                ex -> ex instanceof StreamSegmentNotExistsException);

        // Create a segment, add some data, set some attributes, "truncate" it and then seal it.
        val storageInfo = context.storage.create(SEGMENT_NAME, TIMEOUT)
                .thenCompose(handle -> context.storage.write(handle, 0, new ByteArrayInputStream(new byte[10]), 10, TIMEOUT))
                .thenCompose(v -> context.storage.getStreamSegmentInfo(SEGMENT_NAME, TIMEOUT)).join();
        val expectedInfo = StreamSegmentInformation.from(storageInfo)
                .startOffset(storageInfo.getLength() / 2)
                .attributes(ImmutableMap.of(UUID.randomUUID(), 100L, Attributes.EVENT_COUNT, 1L))
                .build();

        // Fetch the SegmentInfo from the ReadOnlyContainer and verify it is as expected.
        val actual = context.container.getStreamSegmentInfo(SEGMENT_NAME, TIMEOUT).join();
        Assert.assertEquals("Unexpected Name.", expectedInfo.getName(), actual.getName());
        Assert.assertEquals("Unexpected Length.", expectedInfo.getLength(), actual.getLength());
        Assert.assertEquals("Unexpected Sealed status.", expectedInfo.isSealed(), actual.isSealed());
    }