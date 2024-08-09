@Test
    public void testAddAsEvidence() {
        Dependency instance = new Dependency();
        MavenArtifact mavenArtifact = new MavenArtifact("group", "artifact", "version", "url");
        instance.addAsEvidence("pom", mavenArtifact, Confidence.HIGH);
        assertTrue(instance.contains(EvidenceType.VENDOR, Confidence.HIGH));
        assertEquals(3, instance.size());
        assertFalse(instance.getIdentifiers().isEmpty());
    }