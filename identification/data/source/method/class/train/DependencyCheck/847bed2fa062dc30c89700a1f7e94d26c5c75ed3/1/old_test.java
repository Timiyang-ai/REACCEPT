@Test
    public void testAnalyzeDependency() throws Exception {
        Dependency dependency = new Dependency();
        EvidenceCollection versions = dependency.getVersionEvidence();

        versions.addEvidence("util", "version", "33.3", Confidence.HIGHEST);
        versions.addEvidence("other", "version", "alpha", Confidence.HIGHEST);
        versions.addEvidence("manifest", "implementation-version", "1.2.3", Confidence.HIGHEST);

        VersionFilterAnalyzer instance = new VersionFilterAnalyzer();

        instance.analyzeDependency(dependency, null);
        assertEquals(3, versions.size());

        versions.addEvidence("pom", "version", "1.2.3", Confidence.HIGHEST);

        instance.analyzeDependency(dependency, null);
        assertEquals(4, versions.size());

        versions.addEvidence("file", "version", "1.2.3", Confidence.HIGHEST);
        instance.analyzeDependency(dependency, null);
        assertEquals(2, versions.size());

        versions.addEvidence("nexus", "version", "1.2.3", Confidence.HIGHEST);
        versions.addEvidence("other", "version", "alpha", Confidence.HIGHEST);
        instance.analyzeDependency(dependency, null);
        assertEquals(3, versions.size());

        versions.addEvidence("central", "version", "1.2.3", Confidence.HIGHEST);
        versions.addEvidence("other", "version", "alpha", Confidence.HIGHEST);
        instance.analyzeDependency(dependency, null);
        assertEquals(4, versions.size());
    }