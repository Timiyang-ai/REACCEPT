    @Test
    public void getFileCharsetSimple() {
        assertEquals("GBK", FileUtils.getFileCharsetSimple(PATH_FILE + "GBK.txt"));
        assertEquals("Unicode", FileUtils.getFileCharsetSimple(PATH_FILE + "Unicode.txt"));
        assertEquals("UTF-8", FileUtils.getFileCharsetSimple(PATH_FILE + "UTF8.txt"));
        assertEquals("UTF-16BE", FileUtils.getFileCharsetSimple(PATH_FILE + "UTF16BE.txt"));
    }