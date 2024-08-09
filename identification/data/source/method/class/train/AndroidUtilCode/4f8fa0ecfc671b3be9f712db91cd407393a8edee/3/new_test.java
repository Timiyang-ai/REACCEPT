    @Test
    public void encryptSHA384() {
        String blankjSHA384 = "BF831E5221FC108D6A72ACB888BA3EB0C030A5F01BA2F739856BE70681D86F992B85E0D461101C74BAEDA895BD422557";
        assertEquals(
                blankjSHA384,
                EncryptUtils.encryptSHA384ToString("blankj")
        );
        assertEquals(
                blankjSHA384,
                EncryptUtils.encryptSHA384ToString("blankj".getBytes())
        );
        assertArrayEquals(
                hexString2Bytes(blankjSHA384),
                EncryptUtils.encryptSHA384("blankj".getBytes())
        );
    }