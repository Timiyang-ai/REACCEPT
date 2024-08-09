    @Test
    public void createOrExistsDir() {
        assertTrue(FileUtils.createOrExistsDir(PATH_FILE + "new Dir"));
        assertTrue(FileUtils.createOrExistsDir(PATH_FILE));
        assertTrue(FileUtils.delete(PATH_FILE + "new Dir"));
    }