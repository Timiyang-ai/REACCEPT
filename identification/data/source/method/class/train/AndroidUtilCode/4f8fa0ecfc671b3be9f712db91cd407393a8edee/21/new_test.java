    @Test
    public void encryptSHA1() {
        String blankjSHA1 = "C606ACCB1FEB669E19D080ADDDDBB8E6CDA5F43C";
        assertEquals(
                blankjSHA1,
                EncryptUtils.encryptSHA1ToString("blankj")
        );
        assertEquals(
                blankjSHA1,
                EncryptUtils.encryptSHA1ToString("blankj".getBytes())
        );
        assertArrayEquals(
                hexString2Bytes(blankjSHA1),
                EncryptUtils.encryptSHA1("blankj".getBytes())
        );
    }