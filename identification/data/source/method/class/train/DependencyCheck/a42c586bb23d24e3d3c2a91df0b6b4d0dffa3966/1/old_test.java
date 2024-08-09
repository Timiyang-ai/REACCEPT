@Test
    public void testGetFile() {
        String key = Settings.KEYS.CPE_DATA_DIRECTORY;
        String expResult = "data" + File.separator + "cpe";
        File result = Settings.getFile(key);
        Assert.assertTrue(result.getAbsolutePath().endsWith(expResult));

        key = "an invalid key!!!";
        result = Settings.getFile(key, expResult);
        Assert.assertTrue(result.getAbsolutePath().endsWith(expResult));
    }