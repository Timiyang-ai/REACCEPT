public EnvironmentVersion getEnvironmentVersion(final String testName) {
        final List<Revision> trunkHistory, qaHistory, productionHistory;
        // Fetch versions in parallel
        final Future<List<Revision>> trunkFuture = executor.submit(new GetEnvironmentVersionTask(trunk, testName));
        final Future<List<Revision>> qaFuture = executor.submit(new GetEnvironmentVersionTask(qa, testName));
        final Future<List<Revision>> productionFuture = executor.submit(new GetEnvironmentVersionTask(production, testName));
        try {
            trunkHistory = trunkFuture.get(30, TimeUnit.SECONDS);
            qaHistory = qaFuture.get(30, TimeUnit.SECONDS);
            productionHistory = productionFuture.get(30, TimeUnit.SECONDS);
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

        final Revision trunkRevision = trunkHistory.isEmpty() ? null : trunkHistory.get(0);
        final Revision qaRevision = qaHistory.isEmpty() ? null : qaHistory.get(0);
        final Revision productionRevision = productionHistory.isEmpty() ? null : productionHistory.get(0);

        final String trunkVersion = GitProctorUtils.resolveSvnMigratedRevision(trunkRevision, Environment.WORKING.getName());
        final String qaVersion = getEffectiveRevisionFromStore(qa, testName, qaRevision);
        final String productionVersion = getEffectiveRevisionFromStore(production, testName, productionRevision);

        return new EnvironmentVersion(testName, trunkRevision, trunkVersion, qaRevision, qaVersion, productionRevision, productionVersion);
    }