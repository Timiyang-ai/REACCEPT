@Test
    public void getFileNameTest() throws Exception {
        assertEquals("test",CompressedHelper.getFileName("test.zip"));
        assertEquals("test",CompressedHelper.getFileName("test.rar"));
        assertEquals("test",CompressedHelper.getFileName("test.tar"));
        assertEquals("test",CompressedHelper.getFileName("test.tar.gz"));
        assertEquals("test",CompressedHelper.getFileName("test.jar"));
        assertEquals("test",CompressedHelper.getFileName("test.apk"));
        assertEquals("test",CompressedHelper.getFileName("test.7z"));

        //no extension(directory)
        assertEquals("test",CompressedHelper.getFileName("test"));

        //invalid extension
        assertEquals("test.z",CompressedHelper.getFileName("test.z"));

        //no path
        assertEquals("",CompressedHelper.getFileName(""));
    }