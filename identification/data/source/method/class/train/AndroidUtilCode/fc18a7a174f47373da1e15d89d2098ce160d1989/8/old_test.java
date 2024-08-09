    @Test
    public void hexString2Bytes() {
        assertTrue(
                Arrays.equals(
                        mBytes,
                        ConvertUtils.hexString2Bytes(hexString)
                )
        );
    }