@Test
    public void testSupportsExtensions() {
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
        for (String ext : expResult) {
            assertTrue(ext, instance.accept(new File("test." + ext)));
        }
    }