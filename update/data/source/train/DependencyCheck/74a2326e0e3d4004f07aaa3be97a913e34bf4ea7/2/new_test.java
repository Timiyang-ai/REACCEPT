@Test
    public void testGetTempDirectory() throws Exception {
        File tmp = getSettings().getTempDirectory();
        Assert.assertTrue(tmp.exists());
    }