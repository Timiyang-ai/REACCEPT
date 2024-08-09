@Test
    public void testRead() throws Exception {
        final String context = "Read";
        try (Storage s = createStorage()) {
            // Check invalid segment name.
            assertThrows("read() did not throw for invalid segment name.",
                    () -> s.read("foo_read_1", 0, new byte[1], 0, 1, TIMEOUT),
                    ex -> ex instanceof StreamSegmentNotExistsException);

            HashMap<String, ByteArrayOutputStream> appendData = populate(s, context);

            // Do some reading.
            for (Entry<String, ByteArrayOutputStream> entry : appendData.entrySet()) {
                String segmentName = entry.getKey();
                s.open(segmentName).join();
                byte[] expectedData = entry.getValue().toByteArray();

                for (int offset = 0; offset < expectedData.length / 2; offset++) {
                    int length = expectedData.length - 2 * offset;
                    byte[] readBuffer = new byte[length];
                    int bytesRead = s.read(segmentName, offset, readBuffer, 0, readBuffer.length, TIMEOUT).join();
                    Assert.assertEquals(String.format("Unexpected number of bytes read from offset %d.", offset),
                            length, bytesRead);
                    AssertExtensions.assertArrayEquals(String.format("Unexpected read result from offset %d.", offset),
                            expectedData, offset, readBuffer, 0, bytesRead);
                }
            }

            // Test bad parameters.
            val testSegment = getSegmentName(0, context);
            s.open(testSegment).join();
            byte[] testReadBuffer = new byte[10];
            assertThrows("read() allowed reading with negative read offset.",
                    () -> s.read(getSegmentName(0, context), -1, testReadBuffer, 0, testReadBuffer.length, TIMEOUT),
                    ex -> ex instanceof IllegalArgumentException || ex instanceof ArrayIndexOutOfBoundsException);

            assertThrows("read() allowed reading with offset beyond Segment length.",
                    () -> s.read(testSegment, s.getStreamSegmentInfo(testSegment, TIMEOUT).join().getLength() + 1,
                            testReadBuffer, 0, testReadBuffer.length, TIMEOUT),
                    ex -> ex instanceof IllegalArgumentException || ex instanceof ArrayIndexOutOfBoundsException);

            assertThrows("read() allowed reading with negative read buffer offset.",
                    () -> s.read(testSegment, 0, testReadBuffer, -1, testReadBuffer.length, TIMEOUT),
                    ex -> ex instanceof IllegalArgumentException || ex instanceof ArrayIndexOutOfBoundsException);

            assertThrows("read() allowed reading with invalid read buffer length.",
                    () -> s.read(testSegment, 0, testReadBuffer, 1, testReadBuffer.length, TIMEOUT),
                    ex -> ex instanceof IllegalArgumentException || ex instanceof ArrayIndexOutOfBoundsException);

            assertThrows("read() allowed reading with invalid read length.",
                    () -> s.read(testSegment, 0, testReadBuffer, 0, testReadBuffer.length + 1, TIMEOUT),
                    ex -> ex instanceof IllegalArgumentException || ex instanceof ArrayIndexOutOfBoundsException);

            // Check post-delete read.
            s.delete(testSegment, TIMEOUT).join();
            assertThrows("read() did not throw for a deleted StreamSegment.",
                    () -> s.read(testSegment, 0, new byte[1], 0, 1, TIMEOUT),
                    ex -> ex instanceof StreamSegmentNotExistsException);
        }
    }