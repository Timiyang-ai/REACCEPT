    @Test
    public void bytes2Chars() {
        assertTrue(
                Arrays.equals(
                        mChars1,
                        ConvertUtils.bytes2Chars(mBytes1)
                )
        );
    }