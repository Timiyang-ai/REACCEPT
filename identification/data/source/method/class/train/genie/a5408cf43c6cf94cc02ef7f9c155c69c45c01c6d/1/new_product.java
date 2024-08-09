@Bean
    @ConditionalOnMissingBean(JobDirectoryManifestCreatorService.class)
    public JobDirectoryManifestCreatorServiceImpl jobDirectoryManifestCreatorService(
        final DirectoryManifest.Factory directoryManifestFactory,
        @Qualifier("jobDirectoryManifestCache") final Cache<Path, DirectoryManifest> cache
    ) {
        return new JobDirectoryManifestCreatorServiceImpl(directoryManifestFactory, cache, false);
    }