@Test
    public void testAddCriticalityToVulnerability() throws AnalysisException, DatabaseException {
        try {
            analyzer.initialize(null);
            
            final Dependency result = new Dependency(BaseTest.getResourceAsFile(this,
                    "ruby/vulnerable/gems/sinatra/Gemfile.lock"));
            final Engine engine = new Engine(getSettings());
            analyzer.analyze(result, engine);
            
            Dependency dependency = engine.getDependencies().get(0);
            Vulnerability vulnerability = dependency.getVulnerabilities().first();
            assertEquals(vulnerability.getCvssScore(), 5.0f, 0.0);
            
        } catch (InitializationException | DatabaseException | AnalysisException e) {
            LOGGER.warn("Exception setting up RubyBundleAuditAnalyzer. Make sure Ruby gem bundle-audit is installed. You may also need to set property \"analyzer.bundle.audit.path\".");
            Assume.assumeNoException("Exception setting up RubyBundleAuditAnalyzer; bundle audit may not be installed, or property \"analyzer.bundle.audit.path\" may not be set.", e);
        }
    }