@Test
    public void testRemoveBadMatches() throws Exception {
        Dependency dependency = new Dependency();
        dependency.setFileName("some.jar");
        dependency.setFilePath("some.jar");
        Cpe cpe = builder.part(Part.APPLICATION).vendor("m-core").product("m-core").build();
        CpeIdentifier id = new CpeIdentifier(cpe, Confidence.HIGHEST);
        dependency.addVulnerableSoftwareIdentifier(id);

        assertEquals(1, dependency.getVulnerableSoftwareIdentifiers().size());

        FalsePositiveAnalyzer instance = new FalsePositiveAnalyzer();
        instance.removeBadMatches(dependency);

        assertEquals(0, dependency.getVulnerableSoftwareIdentifiers().size());
        dependency.addVulnerableSoftwareIdentifier(id);
        dependency.addEvidence(EvidenceType.PRODUCT, "test", "name", "m-core", Confidence.HIGHEST);

        instance.removeBadMatches(dependency);
        assertEquals(1, dependency.getVulnerableSoftwareIdentifiers().size());
    }