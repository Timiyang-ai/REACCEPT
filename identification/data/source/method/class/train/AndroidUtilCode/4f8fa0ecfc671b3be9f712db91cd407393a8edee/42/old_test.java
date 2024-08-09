    @Test
    public void encryptAES() {
        assertArrayEquals(
                bytesResAES,
                EncryptUtils.encryptAES(bytesDataAES, bytesKeyAES, "AES/ECB/PKCS5Padding", null)
        );
        assertEquals(
                resAES,
                EncryptUtils.encryptAES2HexString(bytesDataAES, bytesKeyAES, "AES/ECB/PKCS5Padding", null)
        );
        assertArrayEquals(
                base64Encode(bytesResAES),
                EncryptUtils.encryptAES2Base64(bytesDataAES, bytesKeyAES, "AES/ECB/PKCS5Padding", null)
        );
    }