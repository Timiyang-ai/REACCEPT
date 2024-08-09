@Test
    public void testRemoveBadMatches() {
        Dependency dependency = new Dependency();
        dependency.setFileName("some.jar");
        dependency.setFilePath("some.jar");
        dependency.addIdentifier("cpe", "cpe:/a:m-core:m-core", "");

        assertEquals(1, dependency.getIdentifiers().size());

        FalsePositiveAnalyzer instance = new FalsePositiveAnalyzer();
        instance.removeBadMatches(dependency);

        assertEquals(0, dependency.getIdentifiers().size());
        dependency.addIdentifier("cpe", "cpe:/a:m-core:m-core", "");
        dependency.addEvidence(EvidenceType.PRODUCT,"test", "name", "m-core", Confidence.HIGHEST);

        instance.removeBadMatches(dependency);
        assertEquals(1, dependency.getIdentifiers().size());
    }