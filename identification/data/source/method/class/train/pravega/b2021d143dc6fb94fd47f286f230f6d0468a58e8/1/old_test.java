@Test
    public void testCreate() {
        String segmentName = "foo_open";
        try (Storage s = createStorage()) {
            s.initialize(DEFAULT_EPOCH);
            s.create(segmentName, null).join();
            assertThrows("create() did not throw for existing StreamSegment.",
                    s.create(segmentName, null),
                    ex -> ex instanceof StreamSegmentExistsException);
        }
    }