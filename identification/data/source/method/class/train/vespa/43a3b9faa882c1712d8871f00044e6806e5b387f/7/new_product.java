public boolean delete(ApplicationId applicationId) {
        List<String> hostedZonesToUseDeleteApplication =
                Arrays.asList("dev.us-east-1", "dev.corp-us-east-1",
                              "test.us-east-1", "staging.us-east-3",
                              "prod.aws-us-east-1a", "prod.aws-us-west-1b",
                              "prod.corp-us-east-1", "prod.us-central-1");
        boolean useDeleteApplicationLegacy = !hostedZonesToUseDeleteApplication.contains(zone().toString());

        // TODO: Use deleteApplication() in all zones
        if (configserverConfig.deleteApplicationLegacy() ||
                (configserverConfig.hostedVespa() && zone().system() == SystemName.main && useDeleteApplicationLegacy)) {
            return deleteApplicationLegacy(applicationId);
        } else {
            return deleteApplication(applicationId);
        }
    }