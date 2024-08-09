@Test
    public void testRead() throws IOException {
        FileRecords read = fileRecords.read(0, fileRecords.sizeInBytes());
        TestUtils.checkEquals(fileRecords.batches(), read.batches());

        List<RecordBatch> items = batches(read);
        RecordBatch second = items.get(1);

        read = fileRecords.read(second.sizeInBytes(), fileRecords.sizeInBytes());
        assertEquals("Try a read starting from the second message",
                items.subList(1, 3), batches(read));

        read = fileRecords.read(second.sizeInBytes(), second.sizeInBytes());
        assertEquals("Try a read of a single message starting from the second message",
                Collections.singletonList(second), batches(read));
    }