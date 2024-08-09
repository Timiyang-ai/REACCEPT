    @Test
    public void encryptSHA224() {
        String blankjSHA224 = "F4C5C0E8CF56CAC4D06DB6B523F67621859A9D79BDA4B2AC03097D5F";
        assertEquals(
                blankjSHA224,
                EncryptUtils.encryptSHA224ToString("blankj")
        );
        assertEquals(
                blankjSHA224,
                EncryptUtils.encryptSHA224ToString("blankj".getBytes())
        );
        assertArrayEquals(
                hexString2Bytes(blankjSHA224),
                EncryptUtils.encryptSHA224("blankj".getBytes())
        );
    }