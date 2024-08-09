@Test
    public void testGetDataFile() throws IOException {
        String key = Settings.KEYS.DATA_DIRECTORY;
        String expResult = "data";
        File result = getSettings().getDataFile(key);
        Assert.assertTrue(result.getAbsolutePath().endsWith(expResult));
    }