    @Test
    public void encryptSHA256() {
        String blankjSHA256 = "8BD80AE90DFBA112786367BEBDDEE60A638EF5B82682EDF8F3D3CA8E6BFEF648";
        assertEquals(
                blankjSHA256,
                EncryptUtils.encryptSHA256ToString("blankj")
        );
        assertEquals(
                blankjSHA256,
                EncryptUtils.encryptSHA256ToString("blankj".getBytes())
        );
        assertArrayEquals(
                hexString2Bytes(blankjSHA256),
                EncryptUtils.encryptSHA256("blankj".getBytes()));

    }