@Test
    public void testAddAsEvidence() {
        Dependency instance = new Dependency();
        MavenArtifact mavenArtifact = new MavenArtifact("group", "artifact", "version", "url");
        instance.addAsEvidence("pom", mavenArtifact, Confidence.HIGH);
        assertTrue(instance.getEvidence().contains(Confidence.HIGH));
        assertFalse(instance.getEvidence().getEvidence("pom", "groupid").isEmpty());
        assertFalse(instance.getEvidence().getEvidence("pom", "artifactid").isEmpty());
        assertFalse(instance.getEvidence().getEvidence("pom", "version").isEmpty());
        assertFalse(instance.getIdentifiers().isEmpty());
    }