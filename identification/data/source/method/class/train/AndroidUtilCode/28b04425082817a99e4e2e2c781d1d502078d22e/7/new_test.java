    @Test
    public void getFileExtension() {
        assertEquals("txt", FileUtils.getFileExtension(new File(PATH_FILE + "UTF8.txt")));
        assertEquals("txt", FileUtils.getFileExtension(PATH_FILE + "UTF8.txt"));
    }