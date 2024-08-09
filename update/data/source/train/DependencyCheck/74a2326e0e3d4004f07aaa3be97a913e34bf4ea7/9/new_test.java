@Test
    public void testMergeProperties_String() throws IOException, URISyntaxException {
        String key = Settings.KEYS.PROXY_PORT;
        String expResult = getSettings().getString(key);
        File f = new File(this.getClass().getClassLoader().getResource("test.properties").toURI());
        //InputStream in = this.getClass().getClassLoader().getResourceAsStream("test.properties");
        getSettings().mergeProperties(f.getAbsolutePath());
        String result = getSettings().getString(key);
        Assert.assertTrue("setting didn't change?", (expResult == null && result != null) || !expResult.equals(result));
    }