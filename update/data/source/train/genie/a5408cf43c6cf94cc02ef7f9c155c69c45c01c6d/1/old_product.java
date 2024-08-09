@Bean
    @ConditionalOnMissingBean(JobDirectoryManifestService.class)
    public JobDirectoryManifestService jobDirectoryManifestService(
        final DirectoryManifest.Factory directoryManifestFactory,
        @Qualifier("jobDirectoryManifestCache") final Cache<Path, DirectoryManifest> cache
    ) {
        return new JobDirectoryManifestServiceImpl(directoryManifestFactory, cache, false);
    }