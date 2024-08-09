@Test
    public void testGetSupportedExtensions() {
        ArchiveAnalyzer instance = new ArchiveAnalyzer();
        Set<String> expResult = new HashSet<String>();
        expResult.add("zip");
        expResult.add("war");
        expResult.add("ear");
        expResult.add("jar");
        expResult.add("sar");
        expResult.add("apk");
        expResult.add("nupkg");
        expResult.add("tar");
        expResult.add("gz");
        expResult.add("tgz");
        Set result = instance.getSupportedExtensions();
        assertEquals(expResult, result);
    }