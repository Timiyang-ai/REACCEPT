    @Test
    public void getFileByPath() {
        assertNull(FileUtils.getFileByPath(" "));
        assertNotNull(FileUtils.getFileByPath(PATH_FILE));
    }