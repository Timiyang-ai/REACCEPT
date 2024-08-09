    public void test_size_Init() throws Exception {
        assertEquals(0, readOnlyFileChannel.size());
        assertEquals(0, writeOnlyFileChannel.size());
        assertEquals(0, readWriteFileChannel.size());
    }