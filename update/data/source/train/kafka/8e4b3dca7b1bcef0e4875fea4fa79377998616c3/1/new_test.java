@Test
    public void testRead() throws IOException {
        FileRecords read = fileRecords.read(0, fileRecords.sizeInBytes());
        assertEquals(fileRecords.sizeInBytes(), read.sizeInBytes());
        TestUtils.checkEquals(fileRecords.batches(), read.batches());

        List<RecordBatch> items = batches(read);
        RecordBatch first = items.get(0);

        // read from second message until the end
        read = fileRecords.read(first.sizeInBytes(), fileRecords.sizeInBytes() - first.sizeInBytes());
        assertEquals(fileRecords.sizeInBytes() - first.sizeInBytes(), read.sizeInBytes());
        assertEquals("Read starting from the second message", items.subList(1, items.size()), batches(read));

        // read from second message and size is past the end of the file
        read = fileRecords.read(first.sizeInBytes(), fileRecords.sizeInBytes());
        assertEquals(fileRecords.sizeInBytes() - first.sizeInBytes(), read.sizeInBytes());
        assertEquals("Read starting from the second message", items.subList(1, items.size()), batches(read));

        // read from second message and position + size overflows
        read = fileRecords.read(first.sizeInBytes(), Integer.MAX_VALUE);
        assertEquals(fileRecords.sizeInBytes() - first.sizeInBytes(), read.sizeInBytes());
        assertEquals("Read starting from the second message", items.subList(1, items.size()), batches(read));

        // read from second message and size is past the end of the file on a view/slice
        read = fileRecords.read(1, fileRecords.sizeInBytes() - 1)
                .read(first.sizeInBytes() - 1, fileRecords.sizeInBytes());
        assertEquals(fileRecords.sizeInBytes() - first.sizeInBytes(), read.sizeInBytes());
        assertEquals("Read starting from the second message", items.subList(1, items.size()), batches(read));

        // read from second message and position + size overflows on a view/slice
        read = fileRecords.read(1, fileRecords.sizeInBytes() - 1)
                .read(first.sizeInBytes() - 1, Integer.MAX_VALUE);
        assertEquals(fileRecords.sizeInBytes() - first.sizeInBytes(), read.sizeInBytes());
        assertEquals("Read starting from the second message", items.subList(1, items.size()), batches(read));

        // read a single message starting from second message
        RecordBatch second = items.get(1);
        read = fileRecords.read(first.sizeInBytes(), second.sizeInBytes());
        assertEquals(second.sizeInBytes(), read.sizeInBytes());
        assertEquals("Read a single message starting from the second message",
                Collections.singletonList(second), batches(read));
    }