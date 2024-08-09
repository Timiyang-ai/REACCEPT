    public static Test suite() {
        return emptyConfiguration().failOnException(Level.SEVERE).failOnMessage(Level.SEVERE)
                .addTest(ProfilerValidationTest.class,
                        "testCreateProject",
                        "testMenus",
                        "testOptions",
                        "testProfiler")
                .suite();
    }