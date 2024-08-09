@Test
    public void testAddCriticalityToVulnerability() throws AnalysisException, DatabaseException {
        try (Engine engine = new Engine(getSettings())) {
            engine.doUpdates(true);
            analyzer.prepare(engine);

            final Dependency result = new Dependency(BaseTest.getResourceAsFile(this,
                    "ruby/vulnerable/gems/sinatra/Gemfile.lock"));
            analyzer.analyze(result, engine);
            Dependency dependency = engine.getDependencies()[0];
            Vulnerability vulnerability = dependency.getVulnerabilities(true).iterator().next();
            assertEquals(vulnerability.getCvssScore(), 5.0f, 0.0);

        } catch (InitializationException | DatabaseException | AnalysisException | UpdateException e) {
            LOGGER.warn("Exception setting up RubyBundleAuditAnalyzer. Make sure Ruby gem bundle-audit is installed. You may also need to set property \"analyzer.bundle.audit.path\".");
            Assume.assumeNoException("Exception setting up RubyBundleAuditAnalyzer; bundle audit may not be installed, or property \"analyzer.bundle.audit.path\" may not be set.", e);
        }
    }