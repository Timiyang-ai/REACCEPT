    @Test
    public void bytes2Bits_bits2Bytes() {
        assertEquals(
                "0111111111111010",
                ConvertUtils.bytes2Bits(new byte[]{0x7F, (byte) 0xFA})
        );
        assertEquals(
                "0111111111111010",
                ConvertUtils.bytes2Bits(ConvertUtils.bits2Bytes("111111111111010"))
        );
    }