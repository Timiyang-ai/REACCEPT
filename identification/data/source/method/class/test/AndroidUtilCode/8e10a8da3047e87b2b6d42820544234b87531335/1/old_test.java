    @Test
    public void createFileByDeleteOldFile() {
        assertTrue(FileUtils.createFileByDeleteOldFile(PATH_FILE + "new File"));
        assertFalse(FileUtils.createFileByDeleteOldFile(PATH_FILE));
        assertTrue(FileUtils.delete(PATH_FILE + "new File"));
    }