@Test
    public void testSeal() throws Exception {
        try (Storage s = createStorage()) {
            // Check pre-create seal.
            AssertExtensions.assertThrows(
                    "seal() did not throw for non-existent StreamSegment.",
                    s.seal("foo", TIMEOUT),
                    ex -> ex instanceof StreamSegmentNotExistsException);

            HashMap<String, ByteArrayOutputStream> appendData = populate(s, 10, 10);
            for (String segmentName : appendData.keySet()) {
                s.seal(segmentName, TIMEOUT).join();
                AssertExtensions.assertThrows(
                        "seal() did not throw for an already sealed StreamSegment.",
                        s.seal(segmentName, TIMEOUT),
                        ex -> ex instanceof StreamSegmentSealedException);

                AssertExtensions.assertThrows(
                        "write() did not throw for a sealed StreamSegment.",
                        s.write(segmentName, s.getStreamSegmentInfo(segmentName, TIMEOUT).join().getLength(), new ByteArrayInputStream("g".getBytes()), 1, TIMEOUT),
                        ex -> ex instanceof StreamSegmentSealedException);

                // Check post-delete seal.
                s.delete(segmentName, TIMEOUT).join();
                AssertExtensions.assertThrows(
                        "seal() did not throw for a deleted StreamSegment.",
                        s.seal(segmentName, TIMEOUT),
                        ex -> ex instanceof StreamSegmentNotExistsException);
            }
        }
    }