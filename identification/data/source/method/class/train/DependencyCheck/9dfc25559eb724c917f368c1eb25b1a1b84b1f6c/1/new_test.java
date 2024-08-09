@Test
    public void testGetDataFile() throws IOException {
        String key = Settings.KEYS.CVE_DATA_DIRECTORY;
        String expResult = "data" + File.separator + "cve";
        File result = Settings.getDataFile(key);
        Assert.assertTrue(result.getAbsolutePath().endsWith(expResult));

        result = Settings.getDataFile(Settings.KEYS.DATA_DIRECTORY);
        String path = result.getPath();
        Assert.assertTrue(path.endsWith("data") || path.endsWith("data" + File.separator));
    }