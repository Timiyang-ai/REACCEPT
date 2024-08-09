public boolean delete(ApplicationId applicationId) {
        // TODO: Use deleteApplication() in all zones, for now use it only in non-hosted
        if (configserverConfig.hostedVespa()) {
            return deleteApplicationLegacy(applicationId);
        } else {
            return deleteApplication(applicationId);
        }
    }