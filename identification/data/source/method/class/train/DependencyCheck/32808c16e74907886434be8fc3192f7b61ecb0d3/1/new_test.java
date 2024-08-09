@Test
    public void testAnalyze() throws Exception {

        File file = new File(this.getClass().getClassLoader().getResource("commons-fileupload-1.2.1.jar").getPath());
        File suppression = new File(this.getClass().getClassLoader().getResource("commons-fileupload-1.2.1.suppression.xml").getPath());
        Settings.setBoolean(Settings.KEYS.AUTO_UPDATE, false);
        Settings.setBoolean(Settings.KEYS.ANALYZER_NEXUS_ENABLED, false);
        Engine engine = new Engine();
        engine.scan(file);
        engine.analyzeDependencies();
        Dependency dependency = getDependency(engine, file);
        int cveSize = dependency.getVulnerabilities().size();
        int cpeSize = dependency.getIdentifiers().size();
        assertTrue(cveSize > 0);
        assertTrue(cpeSize > 0);
        Settings.setString(Settings.KEYS.SUPPRESSION_FILE, suppression.getAbsolutePath());
        VulnerabilitySuppressionAnalyzer instance = new VulnerabilitySuppressionAnalyzer();
        instance.initialize();
        instance.analyze(dependency, engine);
        cveSize = cveSize > 1 ? cveSize - 2 : 0;
        cpeSize = cpeSize > 0 ? cpeSize - 1 : 0;
        assertTrue(dependency.getVulnerabilities().size() == cveSize);
        assertTrue(dependency.getIdentifiers().size() == cpeSize);
        engine.cleanup();
    }