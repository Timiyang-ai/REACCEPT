    @Test
    public void getFileName() {
        assertEquals("UTF8.txt", FileUtils.getFileName(PATH_FILE + "UTF8.txt"));
        assertEquals("UTF8.txt", FileUtils.getFileName(new File(PATH_FILE + "UTF8.txt")));
    }