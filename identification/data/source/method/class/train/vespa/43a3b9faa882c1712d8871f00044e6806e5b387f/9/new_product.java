public boolean delete(ApplicationId applicationId) {
        // TODO: Use deleteApplication() in all zones
        if ( ! configserverConfig.hostedVespa() || SystemName.from(configserverConfig.system()) == SystemName.cd) {
            return deleteApplication(applicationId);
        } else {
            return deleteApplicationLegacy(applicationId);
        }
    }