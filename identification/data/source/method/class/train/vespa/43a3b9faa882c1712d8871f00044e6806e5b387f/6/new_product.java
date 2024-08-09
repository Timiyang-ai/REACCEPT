public boolean delete(ApplicationId applicationId) {
        Zone zone = new Zone(Environment.from(configserverConfig.environment()), RegionName.from(configserverConfig.region()));
        List<String> hostedZonesToUseDeleteApplication =
                Arrays.asList("dev.us-east-1", "dev.corp-us-east-1", "test.us-east-1",
                              "prod.corp-us-east-1", "prod.aws-us-east-1a", "prod.aws-us-west-1b");
        boolean useDeleteApplicationLegacyInThisZone = !hostedZonesToUseDeleteApplication.contains(zone.toString());

        // TODO: Use deleteApplication() in all zones
        if (configserverConfig.deleteApplicationLegacy() ||
                (configserverConfig.hostedVespa() && SystemName.from(configserverConfig.system()) == SystemName.main &&
                        useDeleteApplicationLegacyInThisZone)) {
            return deleteApplicationLegacy(applicationId);
        } else {
            return deleteApplication(applicationId);
        }
    }