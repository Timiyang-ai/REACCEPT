@Test
    public void testGetAnalyzers() {
        AnalyzerService instance = new AnalyzerService(Thread.currentThread().getContextClassLoader());
        List<Analyzer> result = instance.getAnalyzers();

        boolean found = false;
        for (Analyzer a : result) {
            if ("Jar Analyzer".equals(a.getName())) {
                found = true;
            }
        }
        assertTrue("JarAnalyzer loaded", found);
    }