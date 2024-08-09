    @Test
    public void getFileLines() {
        assertEquals(7, FileUtils.getFileLines(PATH_FILE + "UTF8.txt"));
    }