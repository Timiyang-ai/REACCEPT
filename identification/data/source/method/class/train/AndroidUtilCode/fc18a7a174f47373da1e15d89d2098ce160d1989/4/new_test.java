    @Test
    public void base64Decode_base64Encode() {
        assertArrayEquals("blankj".getBytes(), EncodeUtils.base64Decode(EncodeUtils.base64Encode("blankj")));
        assertArrayEquals("blankj".getBytes(), EncodeUtils.base64Decode(EncodeUtils.base64Encode2String("blankj".getBytes())));
        assertEquals(
                "Ymxhbmtq",
                EncodeUtils.base64Encode2String("blankj".getBytes())
        );
        assertArrayEquals("Ymxhbmtq".getBytes(), EncodeUtils.base64Encode("blankj".getBytes()));
    }