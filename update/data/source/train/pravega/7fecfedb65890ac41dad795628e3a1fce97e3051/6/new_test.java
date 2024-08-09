@Test
    public void testRead() throws Exception {
        final String context = "Read";
        try (Storage s = createStorage()) {
            s.initialize(0);

            // Check invalid segment name.
            assertThrows("read() did not throw for invalid segment name.",
                    () -> s.read(createHandle("foo_read_1", true), 0, new byte[1], 0, 1, TIMEOUT),
                    ex -> ex instanceof StreamSegmentNotExistsException);

            HashMap<String, ByteArrayOutputStream> appendData = populate(s, context);

            // Do some reading.
            for (Entry<String, ByteArrayOutputStream> entry : appendData.entrySet()) {
                String segmentName = entry.getKey();
                val readHandle = s.openRead(segmentName).join();
                byte[] expectedData = entry.getValue().toByteArray();

                for (int offset = 0; offset < expectedData.length / 2; offset++) {
                    int length = expectedData.length - 2 * offset;
                    byte[] readBuffer = new byte[length];
                    int bytesRead = s.read(readHandle, offset, readBuffer, 0, readBuffer.length, TIMEOUT).join();
                    Assert.assertEquals(String.format("Unexpected number of bytes read from offset %d.", offset),
                            length, bytesRead);
                    AssertExtensions.assertArrayEquals(String.format("Unexpected read result from offset %d.", offset),
                            expectedData, offset, readBuffer, 0, bytesRead);
                }
            }

            // Test bad parameters.
            val testSegment = getSegmentName(0, context);
            val testSegmentHandle = s.openRead(testSegment).join();
            byte[] testReadBuffer = new byte[10];
            assertThrows("read() allowed reading with negative read offset.",
                    () -> s.read(createHandle(getSegmentName(0, context), true), -1, testReadBuffer, 0, testReadBuffer.length, TIMEOUT),
                    ex -> ex instanceof IllegalArgumentException || ex instanceof ArrayIndexOutOfBoundsException);

            assertThrows("read() allowed reading with offset beyond Segment length.",
                    () -> s.read(testSegmentHandle, s.getStreamSegmentInfo(testSegment, TIMEOUT).join().getLength() + 1,
                            testReadBuffer, 0, testReadBuffer.length, TIMEOUT),
                    ex -> ex instanceof IllegalArgumentException || ex instanceof ArrayIndexOutOfBoundsException);

            assertThrows("read() allowed reading with negative read buffer offset.",
                    () -> s.read(testSegmentHandle, 0, testReadBuffer, -1, testReadBuffer.length, TIMEOUT),
                    ex -> ex instanceof IllegalArgumentException || ex instanceof ArrayIndexOutOfBoundsException);

            assertThrows("read() allowed reading with invalid read buffer length.",
                    () -> s.read(testSegmentHandle, 0, testReadBuffer, 1, testReadBuffer.length, TIMEOUT),
                    ex -> ex instanceof IllegalArgumentException || ex instanceof ArrayIndexOutOfBoundsException);

            assertThrows("read() allowed reading with invalid read length.",
                    () -> s.read(testSegmentHandle, 0, testReadBuffer, 0, testReadBuffer.length + 1, TIMEOUT),
                    ex -> ex instanceof IllegalArgumentException || ex instanceof ArrayIndexOutOfBoundsException);

            // Check post-delete read.
            s.delete(s.openWrite(testSegment).join(), TIMEOUT).join();
            assertThrows("read() did not throw for a deleted StreamSegment.",
                    () -> s.read(testSegmentHandle, 0, new byte[1], 0, 1, TIMEOUT),
                    ex -> ex instanceof StreamSegmentNotExistsException);
        }
    }