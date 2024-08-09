@Test
    public void testRead() throws Exception {
        try (Storage s = createStorage()) {
            // Check invalid handle.
            assertThrows("read() did not throw for invalid handle.",
                    () -> s.read(createInvalidHandle("foo"), 0, new byte[1], 0, 1, TIMEOUT),
                    ex -> ex instanceof InvalidSegmentHandleException);

            HashMap<String, ByteArrayOutputStream> appendData = populate(s);

            // Do some reading.
            for (String segmentName : appendData.keySet()) {
                val handle = s.open(segmentName, TIMEOUT).join();
                byte[] expectedData = appendData.get(segmentName).toByteArray();

                for (int offset = 0; offset < expectedData.length / 2; offset++) {
                    int length = expectedData.length - 2 * offset;
                    byte[] readBuffer = new byte[length];
                    int bytesRead = s.read(handle, offset, readBuffer, 0, readBuffer.length, TIMEOUT).join();
                    Assert.assertEquals(String.format("Unexpected number of bytes read from offset %d.", offset), length, bytesRead);
                    AssertExtensions.assertArrayEquals(String.format("Unexpected read result from offset %d.", offset), expectedData, offset, readBuffer, 0, bytesRead);
                }
            }

            // Test bad parameters.
            val testHandle = s.open(getSegmentName(0), TIMEOUT).join();
            byte[] testReadBuffer = new byte[10];
            assertThrows("read() allowed reading with negative read offset.",
                    () -> s.read(testHandle, -1, testReadBuffer, 0, testReadBuffer.length, TIMEOUT),
                    ex -> ex instanceof IllegalArgumentException || ex instanceof ArrayIndexOutOfBoundsException);

            assertThrows("read() allowed reading with offset beyond Segment length.",
                    () -> s.read(testHandle, s.getStreamSegmentInfo(testHandle, TIMEOUT).join().getLength() + 1, testReadBuffer, 0, testReadBuffer.length, TIMEOUT),
                    ex -> ex instanceof IllegalArgumentException || ex instanceof ArrayIndexOutOfBoundsException);

            assertThrows("read() allowed reading with negative read buffer offset.",
                    () -> s.read(testHandle, 0, testReadBuffer, -1, testReadBuffer.length, TIMEOUT),
                    ex -> ex instanceof IllegalArgumentException || ex instanceof ArrayIndexOutOfBoundsException);

            assertThrows("read() allowed reading with invalid read buffer length.",
                    () -> s.read(testHandle, 0, testReadBuffer, 1, testReadBuffer.length, TIMEOUT),
                    ex -> ex instanceof IllegalArgumentException || ex instanceof ArrayIndexOutOfBoundsException);

            assertThrows("read() allowed reading with invalid read length.",
                    () -> s.read(testHandle, 0, testReadBuffer, 0, testReadBuffer.length + 1, TIMEOUT),
                    ex -> ex instanceof IllegalArgumentException || ex instanceof ArrayIndexOutOfBoundsException);

            // Check post-delete read.
            s.delete(testHandle, TIMEOUT).join();
            assertThrows("read() did not throw for a deleted StreamSegment.",
                    () -> s.read(testHandle, 0, new byte[1], 0, 1, TIMEOUT),
                    ex -> ex instanceof StreamSegmentNotExistsException);
        }
    }