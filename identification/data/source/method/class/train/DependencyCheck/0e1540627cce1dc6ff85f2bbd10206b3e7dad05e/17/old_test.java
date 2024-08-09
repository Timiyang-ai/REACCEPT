@Test
    public void testAnalyzeDependency() throws Exception {
        Dependency dependency = new Dependency();
        dependency.setFileName("pom.xml");
        dependency.setFilePath("pom.xml");
        dependency.addIdentifier("cpe", "cpe:/a:file:file:1.2.1", "http://some.org/url");
        Engine engine = null;
        FalsePositiveAnalyzer instance = new FalsePositiveAnalyzer();
        int before = dependency.getIdentifiers().size();
        instance.analyze(dependency, engine);
        int after = dependency.getIdentifiers().size();
        assertTrue(before > after);
    }