    @Test
    public void encryptHmacSHA224() {
        String blankjHmacSHA224 = "E392D83D1030323FB2E062E8165A3AD38366E53DF19EA3290961E153";
        assertEquals(
                blankjHmacSHA224,
                EncryptUtils.encryptHmacSHA224ToString("blankj", blankjHmackey)
        );
        assertEquals(
                blankjHmacSHA224,
                EncryptUtils.encryptHmacSHA224ToString("blankj".getBytes(), blankjHmackey.getBytes())
        );
        assertArrayEquals(
                hexString2Bytes(blankjHmacSHA224),
                EncryptUtils.encryptHmacSHA224("blankj".getBytes(), blankjHmackey.getBytes())
        );
    }