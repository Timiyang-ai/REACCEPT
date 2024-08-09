    @Test
    public void isFileExists() {
        assertTrue(FileUtils.isFileExists(PATH_FILE + "UTF8.txt"));
        assertFalse(FileUtils.isFileExists(PATH_FILE + "UTF8"));
    }