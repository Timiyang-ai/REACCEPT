public boolean delete(ApplicationId applicationId) {
        return configserverConfig.deleteApplicationLegacy() ? deleteApplicationLegacy(applicationId) : deleteApplication(applicationId);
    }