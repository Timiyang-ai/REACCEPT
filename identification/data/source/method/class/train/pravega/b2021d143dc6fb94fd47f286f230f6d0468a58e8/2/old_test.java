@Test
    public void testAppend() throws Exception {
        final String segmentName = "segment";

        @Cleanup
        val storage = new InMemoryStorage();
        storage.initialize(DEFAULT_EPOCH);
        storage.create(segmentName, TIMEOUT).get(TIMEOUT.toMillis(), TimeUnit.MILLISECONDS);

        val handle = storage.openWrite(segmentName).join();
        ByteArrayOutputStream writeStream = new ByteArrayOutputStream();

        for (int j = 0; j < APPENDS_PER_SEGMENT; j++) {
            byte[] writeData = String.format("Segment_%s_Append_%d", segmentName, j).getBytes();
            ByteArrayInputStream dataStream = new ByteArrayInputStream(writeData);
            storage.append(handle, dataStream, writeData.length);
            writeStream.write(writeData);
        }

        byte[] expectedData = writeStream.toByteArray();
        byte[] readBuffer = new byte[expectedData.length];
        int bytesRead = storage.read(handle, 0, readBuffer, 0, readBuffer.length, TIMEOUT).join();
        Assert.assertEquals("Unexpected number of bytes read.", readBuffer.length, bytesRead);
        AssertExtensions.assertArrayEquals("Unexpected read result.", expectedData, 0, readBuffer, 0, bytesRead);
    }