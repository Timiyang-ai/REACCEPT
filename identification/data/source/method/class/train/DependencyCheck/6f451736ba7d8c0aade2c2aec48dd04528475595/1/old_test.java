@Test
    public void testGetAnalyzers() {
        AnalyzerService instance = new AnalyzerService(Thread.currentThread().getContextClassLoader());
        Iterator<Analyzer> result = instance.getAnalyzers();

        boolean found = false;
        while (result.hasNext()) {
            Analyzer a = result.next();
            if ("Jar Analyzer".equals(a.getName())) {
                found = true;
            }
        }
        assertTrue("JarAnalyzer loaded", found);
    }