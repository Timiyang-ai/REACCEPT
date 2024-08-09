    @Test
    public void encryptHmacSHA384() {
        String blankjHmacSHA384 = "9FC2F49C7EDE698EA59645B3BEFBBE67DCC7D6623E03D4D03CDA1324F7B6445BC428AB42F6A962CF79AFAD1302C3223D";
        assertEquals(
                blankjHmacSHA384,
                EncryptUtils.encryptHmacSHA384ToString("blankj", blankjHmackey)
        );
        assertEquals(
                blankjHmacSHA384,
                EncryptUtils.encryptHmacSHA384ToString("blankj".getBytes(), blankjHmackey.getBytes())
        );
        assertArrayEquals(
                hexString2Bytes(blankjHmacSHA384),
                EncryptUtils.encryptHmacSHA384("blankj".getBytes(), blankjHmackey.getBytes())
        );
    }