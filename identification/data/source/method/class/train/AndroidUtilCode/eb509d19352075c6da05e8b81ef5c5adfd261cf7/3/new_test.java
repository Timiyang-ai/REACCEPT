    @Test
    public void copyFile() {
        assertFalse(FileUtils.copy(PATH_FILE + "GBK.txt", PATH_FILE + "GBK.txt", mListener));
        assertTrue(FileUtils.copy(PATH_FILE + "GBK.txt", PATH_FILE + "new Dir" + FILE_SEP + "GBK.txt", mListener));
        assertTrue(FileUtils.copy(PATH_FILE + "GBK.txt", PATH_TEMP + "GBK.txt", mListener));
        assertTrue(FileUtils.delete(PATH_FILE + "new Dir"));
        assertTrue(FileUtils.delete(PATH_TEMP));
    }