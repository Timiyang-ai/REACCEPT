public boolean delete(ApplicationId applicationId) {
        // TODO: Use deleteApplication() in all zones
        if (configserverConfig.deleteApplicationLegacy() ||
                (configserverConfig.hostedVespa() && SystemName.from(configserverConfig.system()) == SystemName.main
                        && !Arrays.asList("corp-us-east-1", "aws-us-east-1a").contains(configserverConfig.region()))) {
            return deleteApplicationLegacy(applicationId);
        } else {
            return deleteApplication(applicationId);
        }
    }