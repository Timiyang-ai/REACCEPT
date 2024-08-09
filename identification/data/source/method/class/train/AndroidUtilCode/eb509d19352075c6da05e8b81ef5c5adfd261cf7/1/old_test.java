    @Test
    public void moveFile() {
        assertFalse(FileUtils.move(PATH_FILE + "GBK.txt", PATH_FILE + "GBK.txt", mListener));
        assertTrue(FileUtils.move(PATH_FILE + "GBK.txt", PATH_TEMP + "GBK.txt", mListener));
        assertTrue(FileUtils.move(PATH_TEMP + "GBK.txt", PATH_FILE + "GBK.txt", mListener));
        FileUtils.delete(PATH_TEMP);
    }