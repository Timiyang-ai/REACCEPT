@Test
    public void testAnalyzeDependency() throws Exception {
        Dependency dependency = new Dependency();
        dependency.setFileName("pom.xml");
        dependency.setFilePath("pom.xml");
        Cpe cpe = builder.part(Part.APPLICATION).vendor("file").product("file").version("1.2.1").build();
        CpeIdentifier id = new CpeIdentifier(cpe, "http://some.org/url", Confidence.HIGHEST);
        dependency.addVulnerableSoftwareIdentifier(id);
        Engine engine = null;
        FalsePositiveAnalyzer instance = new FalsePositiveAnalyzer();
        int before = dependency.getVulnerableSoftwareIdentifiers().size();
        instance.analyze(dependency, engine);
        int after = dependency.getVulnerableSoftwareIdentifiers().size();
        assertTrue(before > after);
    }