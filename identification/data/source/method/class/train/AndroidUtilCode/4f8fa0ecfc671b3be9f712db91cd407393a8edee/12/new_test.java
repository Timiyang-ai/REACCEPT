    @Test
    public void chars2Bytes() {
        assertTrue(
                Arrays.equals(
                        mBytes1,
                        ConvertUtils.chars2Bytes(mChars1)
                )
        );
    }