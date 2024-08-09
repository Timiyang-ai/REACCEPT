    @Test
    public void encryptMD5File() {
        String fileMd5 = "7f138a09169b250e9dcb378140907378";
        assertEquals(
                fileMd5.toUpperCase(),
                EncryptUtils.encryptMD5File2String(new File(PATH_ENCRYPT + "MD5.txt"))
        );
    }