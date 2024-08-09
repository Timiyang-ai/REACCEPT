    @Test
    public void getFileNameNoExtension() {
        assertEquals("UTF8", FileUtils.getFileNameNoExtension(PATH_FILE + "UTF8.txt"));
        assertEquals("UTF8", FileUtils.getFileNameNoExtension(new File(PATH_FILE + "UTF8.txt")));
    }