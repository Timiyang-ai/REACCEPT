    @Test
    public void createOrExistsFile() {
        assertTrue(FileUtils.createOrExistsFile(PATH_FILE + "new File"));
        assertFalse(FileUtils.createOrExistsFile(PATH_FILE));
        assertTrue(FileUtils.delete(PATH_FILE + "new File"));
    }