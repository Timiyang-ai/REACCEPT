    @Test
    public void encryptHmacSHA512() {
        assertEquals(
                blankjHmacSHA512,
                EncryptUtils.encryptHmacSHA512ToString("blankj", blankjHmackey)
        );
        assertEquals(
                blankjHmacSHA512,
                EncryptUtils.encryptHmacSHA512ToString("blankj".getBytes(), blankjHmackey.getBytes())
        );
        assertArrayEquals(
                hexString2Bytes(blankjHmacSHA512),
                EncryptUtils.encryptHmacSHA512("blankj".getBytes(), blankjHmackey.getBytes())
        );
    }