    @Test
    public void inputStream2String_string2InputStream() {
        String string = "this is test string";
        assertEquals(
                string,
                ConvertUtils.inputStream2String(ConvertUtils.string2InputStream(string, "UTF-8"), "UTF-8")
        );
    }