@Test
    public void testSeal() throws Exception {
        final String context = "Seal";
        try (Storage s = createStorage()) {
            s.initialize(0);

            // Check segment not exists.
            assertThrows("seal() did not throw for non-existent segment name.",
                    () -> s.seal(createHandle("foo", false), TIMEOUT),
                    ex -> ex instanceof StreamSegmentNotExistsException);

            HashMap<String, ByteArrayOutputStream> appendData = populate(s, context);
            for (String segmentName : appendData.keySet()) {
                val readHandle = s.openRead(segmentName).join();
                assertThrows("seal() did not throw for read-only handle.",
                        () -> s.seal(readHandle, TIMEOUT),
                        ex -> ex instanceof IllegalArgumentException);

                val writeHandle = s.openWrite(segmentName).join();
                val segmentInfo = s.seal(writeHandle, TIMEOUT).join();
                Assert.assertTrue("seal() did not return a segmentInfo with isSealed == true.", segmentInfo.isSealed());

                //Seal is idempotent. Resealing an already sealed segment should work.
                val segmentInfo1 = s.seal(writeHandle, TIMEOUT).join();
                Assert.assertTrue("seal() did not return a segmentInfo with isSealed == true for an already sealed segment.", segmentInfo1.isSealed());

                assertThrows("write() did not throw for a sealed StreamSegment.",
                        () -> s.write(writeHandle, s.getStreamSegmentInfo(segmentName, TIMEOUT).
                                join().getLength(), new ByteArrayInputStream("g".getBytes()), 1, TIMEOUT),
                        ex -> ex instanceof StreamSegmentSealedException);

                // Check post-delete seal.
                s.delete(writeHandle, TIMEOUT).join();
                assertThrows("seal() did not throw for a deleted StreamSegment.",
                        () -> s.seal(writeHandle, TIMEOUT),
                        ex -> ex instanceof StreamSegmentNotExistsException);
            }
        }
    }