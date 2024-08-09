    @Test
    public void isDir() {
        assertFalse(FileUtils.isDir(PATH_FILE + "UTF8.txt"));
        assertTrue(FileUtils.isDir(PATH_FILE));
    }