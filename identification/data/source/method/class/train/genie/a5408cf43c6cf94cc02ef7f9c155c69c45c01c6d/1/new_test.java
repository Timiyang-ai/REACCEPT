@Test
    public void testJobDirectoryManifestService() {
        this.contextRunner.run(
            context -> {
                Assertions.assertThat(context).hasSingleBean(JobDirectoryManifestCreatorService.class);
            }
        );
    }