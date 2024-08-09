    @Test
    public void getDirName() {
        assertEquals(PATH_FILE, FileUtils.getDirName(new File(PATH_FILE + "UTF8.txt")));
        assertEquals(PATH_FILE, FileUtils.getDirName(PATH_FILE + "UTF8.txt"));
    }