@Test
    public void testAnalyzeDependency() throws Exception {
        Dependency dependency = new Dependency();

        dependency.addEvidence(EvidenceType.VERSION, "util", "version", "33.3", Confidence.HIGHEST);
        dependency.addEvidence(EvidenceType.VERSION, "other", "version", "alpha", Confidence.HIGHEST);
        dependency.addEvidence(EvidenceType.VERSION, "other", "Implementation-Version", "1.2.3", Confidence.HIGHEST);

        VersionFilterAnalyzer instance = new VersionFilterAnalyzer();
        instance.initializeSettings(getSettings());

        instance.analyzeDependency(dependency, null);
        assertEquals(3, dependency.getEvidence(EvidenceType.VERSION).size());

        dependency.addEvidence(EvidenceType.VERSION, "pom", "version", "1.2.3", Confidence.HIGHEST);

        instance.analyzeDependency(dependency, null);
        assertEquals(4, dependency.getEvidence(EvidenceType.VERSION).size());

        dependency.addEvidence(EvidenceType.VERSION, "file", "version", "1.2.3", Confidence.HIGHEST);
        instance.analyzeDependency(dependency, null);
        assertEquals(2, dependency.getEvidence(EvidenceType.VERSION).size());

        dependency.addEvidence(EvidenceType.VERSION, "Manifest", "Implementation-Version", "1.2.3", Confidence.HIGHEST);
        instance.analyzeDependency(dependency, null);
        assertEquals(3, dependency.getEvidence(EvidenceType.VERSION).size());

        dependency.addEvidence(EvidenceType.VERSION, "nexus", "version", "1.2.3", Confidence.HIGHEST);
        dependency.addEvidence(EvidenceType.VERSION, "other", "version", "alpha", Confidence.HIGHEST);
        instance.analyzeDependency(dependency, null);
        assertEquals(4, dependency.getEvidence(EvidenceType.VERSION).size());

        dependency.addEvidence(EvidenceType.VERSION, "central", "version", "1.2.3", Confidence.HIGHEST);
        dependency.addEvidence(EvidenceType.VERSION, "other", "version", "alpha", Confidence.HIGHEST);
        instance.analyzeDependency(dependency, null);
        assertEquals(5, dependency.getEvidence(EvidenceType.VERSION).size());
    }