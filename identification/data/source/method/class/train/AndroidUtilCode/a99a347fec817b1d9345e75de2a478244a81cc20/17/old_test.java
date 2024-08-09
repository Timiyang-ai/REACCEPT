    @Test
    public void encryptHmacMD5() {
        String blankjHmacMD5 = "2BA3FDABEE222522044BEC0CE5D6B490";
        assertEquals(
                blankjHmacMD5,
                EncryptUtils.encryptHmacMD5ToString("blankj", blankjHmackey)
        );
        assertEquals(
                blankjHmacMD5,
                EncryptUtils.encryptHmacMD5ToString("blankj".getBytes(), blankjHmackey.getBytes())
        );
        assertArrayEquals(
                hexString2Bytes(blankjHmacMD5),
                EncryptUtils.encryptHmacMD5("blankj".getBytes(), blankjHmackey.getBytes())
        );
    }