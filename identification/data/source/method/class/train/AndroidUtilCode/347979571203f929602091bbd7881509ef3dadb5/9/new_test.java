    @Test
    public void bytes2HexString() {
        assertEquals(
                hexString,
                ConvertUtils.bytes2HexString(mBytes)
        );
    }