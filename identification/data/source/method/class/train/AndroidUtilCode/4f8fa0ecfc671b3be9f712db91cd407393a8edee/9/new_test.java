    @Test
    public void decryptAES() {
        assertArrayEquals(
                bytesDataAES,
                EncryptUtils.decryptAES(bytesResAES, bytesKeyAES, "AES/ECB/PKCS5Padding", null)
        );
        assertArrayEquals(
                bytesDataAES,
                EncryptUtils.decryptHexStringAES(resAES, bytesKeyAES, "AES/ECB/PKCS5Padding", null)
        );
        assertArrayEquals(bytesDataAES,
                EncryptUtils.decryptBase64AES(base64Encode(bytesResAES), bytesKeyAES, "AES/ECB/PKCS5Padding", null)
        );
    }