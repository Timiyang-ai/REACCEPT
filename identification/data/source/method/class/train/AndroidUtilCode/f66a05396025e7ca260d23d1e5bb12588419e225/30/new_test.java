    @Test
    public void isFile() {
        assertTrue(FileUtils.isFile(PATH_FILE + "UTF8.txt"));
        assertFalse(FileUtils.isFile(PATH_FILE));
    }