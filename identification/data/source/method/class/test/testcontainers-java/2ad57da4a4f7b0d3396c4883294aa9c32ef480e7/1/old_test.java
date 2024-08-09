    @Test
    public void forClasspathResource() throws Exception {
        final MountableFile mountableFile = MountableFile.forClasspathResource("mappable-resource/test-resource.txt");

        performChecks(mountableFile);
    }