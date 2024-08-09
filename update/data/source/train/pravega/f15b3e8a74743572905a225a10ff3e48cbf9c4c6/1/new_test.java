@Test
    public void testDelete() {
        @Cleanup
        val context = new TestContext(NO_SNAPSHOT_CONFIG);
        populateSegments(context);

        // 1. Populate and verify first index.
        val sm = context.containerMetadata.getStreamSegmentMetadata(SEGMENT_ID);
        val idx = context.index.forSegment(SEGMENT_ID, TIMEOUT).join();

        // We intentionally delete twice to make sure the operation is idempotent.
        context.index.delete(sm.getName(), TIMEOUT).join();
        context.index.delete(sm.getName(), TIMEOUT).join();

        AssertExtensions.assertThrows(
                "put() worked after delete().",
                () -> idx.put(UUID.randomUUID(), 0L, TIMEOUT),
                ex -> ex instanceof StreamSegmentNotExistsException);

        // Check index before sealing.
        checkIndex(idx, Collections.emptyMap());
    }