@Test
    public void testDelete() throws Exception {

        File file = File.createTempFile("tmp", "deleteme");
        if (!file.exists()) {
            fail("Unable to create a temporary file.");
        }
        boolean status = FileUtils.delete(file);
        assertTrue("delete returned a failed status", status);
        assertFalse("Temporary file exists after attempting deletion", file.exists());
    }