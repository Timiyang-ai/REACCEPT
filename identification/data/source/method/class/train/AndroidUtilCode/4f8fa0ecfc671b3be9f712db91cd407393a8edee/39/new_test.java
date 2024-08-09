    @Test
    public void encryptSHA512() {
        String blankjSHA512 = "D59D31067F614ED3586F85A31FEFDB7F33096316DA26EBE0FF440B241C8560D96650F100D78C512560C976949EFA89CB5D5589DCF68C7FAADE98F03BCFEC2B45";
        assertEquals(
                blankjSHA512,
                EncryptUtils.encryptSHA512ToString("blankj")
        );
        assertEquals(
                blankjSHA512,
                EncryptUtils.encryptSHA512ToString("blankj".getBytes())
        );
        assertArrayEquals(
                hexString2Bytes(blankjSHA512),
                EncryptUtils.encryptSHA512("blankj".getBytes())
        );
    }