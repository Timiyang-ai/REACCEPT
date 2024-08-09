    @Test
    public void createExclusion() throws Exception {
        String testExclusion = "group";
        Exclusion exclusion = AetherUtils.createExclusion(testExclusion);

        assertEquals("group", exclusion.getGroupId());
        assertEquals("*", exclusion.getArtifactId());
        assertEquals("*", exclusion.getClassifier());
        assertEquals("*", exclusion.getExtension());

        testExclusion = "group:artifact";
        exclusion = AetherUtils.createExclusion(testExclusion);

        assertEquals("group", exclusion.getGroupId());
        assertEquals("artifact", exclusion.getArtifactId());
        assertEquals("*", exclusion.getClassifier());
        assertEquals("*", exclusion.getExtension());

        testExclusion = "group:artifact:site";
        exclusion = AetherUtils.createExclusion(testExclusion);

        assertEquals("group", exclusion.getGroupId());
        assertEquals("artifact", exclusion.getArtifactId());
        assertEquals("site", exclusion.getClassifier());
        assertEquals("*", exclusion.getExtension());

        testExclusion = "group:artifact:site:jar";
        exclusion = AetherUtils.createExclusion(testExclusion);

        assertEquals("group", exclusion.getGroupId());
        assertEquals("artifact", exclusion.getArtifactId());
        assertEquals("site", exclusion.getClassifier());
        assertEquals("jar", exclusion.getExtension());
    }