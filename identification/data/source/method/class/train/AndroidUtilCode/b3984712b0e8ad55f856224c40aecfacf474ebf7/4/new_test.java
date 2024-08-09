    @Test
    public void byte2MemorySize() {
        assertEquals(
                1024,
                ConvertUtils.byte2MemorySize(MemoryConstants.GB, MemoryConstants.MB),
                0.001
        );
    }