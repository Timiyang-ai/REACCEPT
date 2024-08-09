    @Test
    public void isUtf8() {
        assertTrue(FileUtils.isUtf8(PATH_FILE + "UTF8.txt"));
        assertFalse(FileUtils.isUtf8(PATH_FILE + "UTF16BE.txt"));
        assertFalse(FileUtils.isUtf8(PATH_FILE + "Unicode.txt"));
    }