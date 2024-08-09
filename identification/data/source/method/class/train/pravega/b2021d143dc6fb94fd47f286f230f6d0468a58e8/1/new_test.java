@Test
    public void testCreate() {
        String segmentName = "foo_open";
        try (Storage s = createStorage()) {
            s.initialize(DEFAULT_EPOCH);
            createSegment(segmentName, s);
            Assert.assertTrue("Expected the segment to exist.", s.exists(segmentName, null).join());
            assertThrows("create() did not throw for existing StreamSegment.",
                    () -> createSegment(segmentName, s),
                    ex -> ex instanceof StreamSegmentExistsException);

            // Delete and make sure it can be recreated.
            s.openWrite(segmentName).thenCompose(handle -> s.delete(handle, null)).join();
            createSegment(segmentName, s);
            Assert.assertTrue("Expected the segment to exist.", s.exists(segmentName, null).join());
        }
    }