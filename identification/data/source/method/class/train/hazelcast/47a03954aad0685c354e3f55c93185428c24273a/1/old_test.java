    @Test
    public void toHeapData() throws Exception {
        Data data = ToHeapDataConverter.toHeapData(new AnotherDataImpl());
        assertInstanceOf(HeapData.class, data);
    }