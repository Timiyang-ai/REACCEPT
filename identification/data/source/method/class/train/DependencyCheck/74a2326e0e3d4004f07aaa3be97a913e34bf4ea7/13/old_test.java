@Test
    public void testGetTempDirectory() throws Exception {
        File tmp = Settings.getTempDirectory();
        Assert.assertTrue(tmp.exists());
    }