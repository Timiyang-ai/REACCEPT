    @Test
    public void rename() {
        assertTrue(FileUtils.rename(PATH_FILE + "GBK.txt", "GBK1.txt"));
        assertTrue(FileUtils.rename(PATH_FILE + "GBK1.txt", "GBK.txt"));
    }