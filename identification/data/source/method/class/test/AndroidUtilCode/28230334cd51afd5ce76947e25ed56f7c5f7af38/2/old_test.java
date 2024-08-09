    @Test
    public void encryptDES() {
        assertArrayEquals(
                bytesResDES,
                EncryptUtils.encryptDES(bytesDataDES, bytesKeyDES, "DES/ECB/NoPadding", null)
        );
        assertEquals(
                resDES,
                EncryptUtils.encryptDES2HexString(bytesDataDES, bytesKeyDES, "DES/ECB/NoPadding", null)
        );
        assertArrayEquals(
                base64Encode(bytesResDES),
                EncryptUtils.encryptDES2Base64(bytesDataDES, bytesKeyDES, "DES/ECB/NoPadding", null)
        );
    }