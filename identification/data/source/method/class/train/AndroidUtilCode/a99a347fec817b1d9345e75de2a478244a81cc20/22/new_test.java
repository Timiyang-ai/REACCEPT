    @Test
    public void encryptHmacSHA256() {
        String blankjHmacSHA256 = "A59675F13FC9A6E06D8DC90D4DC01DB9C991B0B95749D2471E588BF311DA2C67";
        assertEquals(
                blankjHmacSHA256,
                EncryptUtils.encryptHmacSHA256ToString("blankj", blankjHmackey)
        );
        assertEquals(
                blankjHmacSHA256,
                EncryptUtils.encryptHmacSHA256ToString("blankj".getBytes(), blankjHmackey.getBytes())
        );
        assertArrayEquals(
                hexString2Bytes(blankjHmacSHA256),
                EncryptUtils.encryptHmacSHA256("blankj".getBytes(), blankjHmackey.getBytes())
        );
    }