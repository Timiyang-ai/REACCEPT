    @Test
    public void encryptHmacSHA1() {
        String blankjHmacSHA1 = "88E83EFD915496860C83739BE2CF4752B2AC105F";
        assertEquals(
                blankjHmacSHA1,
                EncryptUtils.encryptHmacSHA1ToString("blankj", blankjHmackey)
        );
        assertEquals(
                blankjHmacSHA1,
                EncryptUtils.encryptHmacSHA1ToString("blankj".getBytes(), blankjHmackey.getBytes())
        );
        assertArrayEquals(
                hexString2Bytes(blankjHmacSHA1),
                EncryptUtils.encryptHmacSHA1("blankj".getBytes(), blankjHmackey.getBytes())
        );
    }