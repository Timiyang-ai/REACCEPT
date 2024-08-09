    @Test
    public void encrypt3DES() {
        assertArrayEquals(
                bytesResDES3,
                EncryptUtils.encrypt3DES(bytesDataDES3, bytesKeyDES3, "DESede/ECB/NoPadding", null)
        );
        assertEquals(
                res3DES,
                EncryptUtils.encrypt3DES2HexString(bytesDataDES3, bytesKeyDES3, "DESede/ECB/NoPadding", null)
        );
        assertArrayEquals(
                base64Encode(bytesResDES3),
                EncryptUtils.encrypt3DES2Base64(bytesDataDES3, bytesKeyDES3, "DESede/ECB/NoPadding", null)
        );
    }