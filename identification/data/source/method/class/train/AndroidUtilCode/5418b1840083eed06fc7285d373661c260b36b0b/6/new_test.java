    @Test
    public void inputStream2Bytes_bytes2InputStream() throws Exception {
        String string = "this is test string";
        assertTrue(
                Arrays.equals(
                        string.getBytes("UTF-8"),
                        ConvertUtils.inputStream2Bytes(ConvertUtils.bytes2InputStream(string.getBytes("UTF-8")))
                )
        );
    }