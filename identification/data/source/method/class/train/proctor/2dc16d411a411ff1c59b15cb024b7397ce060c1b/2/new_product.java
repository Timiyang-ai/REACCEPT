@CheckForNull
    public EnvironmentVersion getEnvironmentVersion(final String testName) {

        final SingleEnvironmentVersion trunkEnvironmentVersion;
        final SingleEnvironmentVersion qaEnvironmentVersion;
        final SingleEnvironmentVersion productionEnvironmentVersion;
        // Fetch versions in parallel
        final Future<SingleEnvironmentVersion> trunkFuture = executor.submit(() ->
                TestDefinitionUtil.getResolvedLastVersion(trunk, testName, Environment.WORKING));
        final Future<SingleEnvironmentVersion> qaFuture = executor.submit(() ->
                TestDefinitionUtil.getResolvedLastVersion(qa, testName, Environment.QA));
        final Future<SingleEnvironmentVersion> productionFuture = executor.submit(() ->
                TestDefinitionUtil.getResolvedLastVersion(production, testName, Environment.PRODUCTION));
        try {
            trunkEnvironmentVersion = trunkFuture.get(30, TimeUnit.SECONDS);
            qaEnvironmentVersion = qaFuture.get(30, TimeUnit.SECONDS);
            productionEnvironmentVersion = productionFuture.get(30, TimeUnit.SECONDS);
        } catch (final InterruptedException | ExecutionException e) {
            LOGGER.error("Unable to retrieve latest version for trunk or qa or production", e);
            return null;
        } catch (final TimeoutException e) {
            LOGGER.error("Timed out when retrieving latest version for trunk or qa or production", e);
            trunkFuture.cancel(true);
            qaFuture.cancel(true);
            productionFuture.cancel(true);
            return null;
        }

        return new EnvironmentVersion(
                testName,
                trunkEnvironmentVersion.getRevision(),
                trunkEnvironmentVersion.getVersion(),
                qaEnvironmentVersion.getRevision(),
                qaEnvironmentVersion.getVersion(),
                productionEnvironmentVersion.getRevision(),
                productionEnvironmentVersion.getVersion());
    }