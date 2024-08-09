    @Test
    public void moveDir() {
        assertFalse(FileUtils.move(PATH_FILE, PATH_FILE, mListener));
        assertFalse(FileUtils.move(PATH_FILE, PATH_FILE + "new Dir", mListener));
        assertTrue(FileUtils.move(PATH_FILE, PATH_TEMP, mListener));
        assertTrue(FileUtils.move(PATH_TEMP, PATH_FILE, mListener));
    }