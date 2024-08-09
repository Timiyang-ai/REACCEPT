    @Test
    public void decryptDES() {
        assertArrayEquals(
                bytesDataDES,
                EncryptUtils.decryptDES(bytesResDES, bytesKeyDES, "DES/ECB/NoPadding", null)
        );
        assertArrayEquals(
                bytesDataDES,
                EncryptUtils.decryptHexStringDES(resDES, bytesKeyDES, "DES/ECB/NoPadding", null)
        );
        assertArrayEquals(
                bytesDataDES,
                EncryptUtils.decryptBase64DES(base64Encode(bytesResDES), bytesKeyDES, "DES/ECB/NoPadding", null)
        );
    }