@Test
    public void testGetCurrentReleaseVersion() {
        EngineVersionCheck instance = new EngineVersionCheck(getSettings());
        DependencyVersion minExpResult = new DependencyVersion("1.2.6");
        String release = instance.getCurrentReleaseVersion();
        DependencyVersion result = new DependencyVersion(release);
        assertTrue(minExpResult.compareTo(result) <= 0);
    }