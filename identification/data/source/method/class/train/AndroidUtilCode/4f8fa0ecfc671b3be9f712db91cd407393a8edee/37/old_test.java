    @Test
    public void decrypt3DES() {
        assertArrayEquals(
                bytesDataDES3,
                EncryptUtils.decrypt3DES(bytesResDES3, bytesKeyDES3, "DESede/ECB/NoPadding", null)
        );
        assertArrayEquals(
                bytesDataDES3,
                EncryptUtils.decryptHexString3DES(res3DES, bytesKeyDES3, "DESede/ECB/NoPadding", null)
        );
        assertArrayEquals(
                bytesDataDES3,
                EncryptUtils.decryptBase64_3DES(base64Encode(bytesResDES3), bytesKeyDES3, "DESede/ECB/NoPadding", null)
        );
    }