public EnvironmentVersion getEnvironmentVersion(final String testName) {
        final EnvironmentVersion environmentVersion = environmentVersions.getIfPresent(testName);
        if (environmentVersion != null) {
         return environmentVersion;
        } else {
            final List<Revision> trunkHistory, qaHistory, productionHistory;
            try {
                trunkHistory = getMostRecentHistory(trunk, testName);
                qaHistory = getMostRecentHistory(qa, testName);
                productionHistory = getMostRecentHistory(production, testName);
            } catch (StoreException e) {
                LOGGER.error("Unable to retrieve latest version for trunk or qa or production", e);
                return null;
            }

            final Revision trunkRevision = trunkHistory.isEmpty() ? null : trunkHistory.get(0);
            final Revision qaRevision = qaHistory.isEmpty() ? null : qaHistory.get(0);
            final Revision productionRevision = productionHistory.isEmpty() ? null : productionHistory.get(0);

            final String trunkVersion = GitProctorUtils.getEffectiveRevision(trunkRevision, Environment.WORKING.getName());

            final TestMatrixDefinition qaTestMatrixDefinition, prodTestMatrixDefinition;

            try {
                qaTestMatrixDefinition = qa.getCurrentTestMatrix().getTestMatrixDefinition();
                prodTestMatrixDefinition = production.getCurrentTestMatrix().getTestMatrixDefinition();
            } catch (StoreException e) {
                LOGGER.error("Unable to retrieve test matrix for qa or production", e);
                return null;
            }

            if (qaTestMatrixDefinition == null || prodTestMatrixDefinition == null) {
                LOGGER.error("null test matrix returned for qa or production");
                return null;
            }

            final String qaVersion = identifyEffectiveRevision(qaTestMatrixDefinition.getTests().get(testName), qaRevision);
            final String prodVersion = identifyEffectiveRevision(prodTestMatrixDefinition.getTests().get(testName), productionRevision);

            final EnvironmentVersion newEnvironmentVersion = new EnvironmentVersion(
                testName,
                trunkRevision, trunkVersion,
                qaRevision, qaVersion,
                productionRevision, prodVersion);
            environmentVersions.put(testName, newEnvironmentVersion);
            return newEnvironmentVersion;
        }
    }