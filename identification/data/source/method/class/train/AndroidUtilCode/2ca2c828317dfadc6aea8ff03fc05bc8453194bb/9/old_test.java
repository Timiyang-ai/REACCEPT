    @Test
    public void copyDir() {
        assertFalse(FileUtils.copy(PATH_FILE, PATH_FILE, mListener));
        assertFalse(FileUtils.copy(PATH_FILE, PATH_FILE + "new Dir", mListener));
        assertTrue(FileUtils.copy(PATH_FILE, PATH_TEMP, mListener));
        assertTrue(FileUtils.delete(PATH_TEMP));
    }