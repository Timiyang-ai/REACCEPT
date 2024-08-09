    @Test
    public void byte2FitMemorySize() {
        assertEquals(
                "3.098MB",
                ConvertUtils.byte2FitMemorySize(1024 * 1024 * 3 + 1024 * 100)
        );
    }