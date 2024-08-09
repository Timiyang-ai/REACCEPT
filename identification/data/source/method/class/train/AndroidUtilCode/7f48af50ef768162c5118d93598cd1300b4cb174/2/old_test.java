    @Test
    public void encryptMD5() {
        String blankjMD5 = "AAC25CD336E01C8655F4EC7875445A60";
        assertEquals(
                blankjMD5,
                EncryptUtils.encryptMD5ToString("blankj")
        );
        assertEquals(
                blankjMD5,
                EncryptUtils.encryptMD5ToString("blankj".getBytes())
        );
        assertArrayEquals(
                hexString2Bytes(blankjMD5),
                EncryptUtils.encryptMD5("blankj".getBytes())
        );
    }