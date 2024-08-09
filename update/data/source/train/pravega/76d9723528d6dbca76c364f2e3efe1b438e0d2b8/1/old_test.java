@Test
    public void testSeal() throws Exception {
        try (Storage s = createStorage()) {
            // Check invalid handle.
            assertThrows("seal() did not throw for invalid handle.",
                    () -> s.seal(createInvalidHandle("foo"), TIMEOUT),
                    ex -> ex instanceof InvalidSegmentHandleException);

            HashMap<String, ByteArrayOutputStream> appendData = populate(s);
            for (String segmentName : appendData.keySet()) {
                val handle = s.open(segmentName, TIMEOUT).join();
                s.seal(handle, TIMEOUT).join();
                assertThrows("seal() did not throw for an already sealed StreamSegment.",
                        () -> s.seal(handle, TIMEOUT),
                        ex -> ex instanceof StreamSegmentSealedException);

                assertThrows("write() did not throw for a sealed StreamSegment.",
                        () -> s.write(handle, s.getStreamSegmentInfo(handle, TIMEOUT).join().getLength(), new ByteArrayInputStream("g".getBytes()), 1, TIMEOUT),
                        ex -> ex instanceof StreamSegmentSealedException);

                // Check post-delete seal.
                s.delete(handle, TIMEOUT).join();
                assertThrows("seal() did not throw for a deleted StreamSegment.",
                        () -> s.seal(handle, TIMEOUT),
                        ex -> ex instanceof StreamSegmentNotExistsException);
            }
        }
    }