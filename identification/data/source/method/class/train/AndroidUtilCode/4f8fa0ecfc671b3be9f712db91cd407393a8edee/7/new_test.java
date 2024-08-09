    @Test
    public void encryptMD2() {
        String blankjMD2 = "15435017570D8A73449E25C4622E17A4";
        Assert.assertEquals(
                blankjMD2,
                EncryptUtils.encryptMD2ToString("blankj")
        );
        assertEquals(
                blankjMD2,
                EncryptUtils.encryptMD2ToString("blankj".getBytes())
        );
        assertArrayEquals(
                hexString2Bytes(blankjMD2),
                EncryptUtils.encryptMD2("blankj".getBytes())
        );
    }