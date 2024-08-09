@Test
    public void testDelete() throws Exception {

        File file = File.createTempFile("tmp", "deleteme");
        if (!file.exists()) {
            fail("Unable to create a temporary file.");
        }
        FileUtils.delete(file);
        assertFalse("Temporary file exists after attempting deletion", file.exists());
    }