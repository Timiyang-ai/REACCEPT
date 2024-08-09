public ConfigChangeActions prepare(SessionContext context, DeployLogger logger, PrepareParams params,
                                       Optional<ApplicationSet> currentActiveApplicationSet, Path tenantPath) {
        Preparation prep = new Preparation(context, logger, params, currentActiveApplicationSet, tenantPath);
        prep.preprocess();
        try {
            prep.buildModels();
            prep.makeResult();
            if ( ! params.isDryRun()) {
                prep.writeStateZK();
                prep.writeRotZK();
                prep.distribute();
                prep.reloadDeployFileDistributor();
            }
            return prep.result();
        } catch (IllegalArgumentException e) {
            throw new InvalidApplicationException("Invalid application package", e);
        }
    }