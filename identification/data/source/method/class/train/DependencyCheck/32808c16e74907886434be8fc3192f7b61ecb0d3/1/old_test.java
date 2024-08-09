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
        assertTrue(dependency.getVulnerabilities().size() > 0);
        assertTrue(dependency.getIdentifiers().size() > 0);
        Settings.setString(Settings.KEYS.SUPPRESSION_FILE, suppression.getAbsolutePath());
        VulnerabilitySuppressionAnalyzer instance = new VulnerabilitySuppressionAnalyzer();
        instance.initialize();
        instance.analyze(dependency, engine);
        assertTrue(dependency.getVulnerabilities().size() == 0);
        assertTrue(dependency.getIdentifiers().isEmpty());
        engine.cleanup();
    }