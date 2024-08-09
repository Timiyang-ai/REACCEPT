public ConfigChangeActions prepare(SessionContext context, DeployLogger logger, PrepareParams params,
                                       Optional<ApplicationSet> currentActiveApplicationSet, Path tenantPath, 
                                       Instant now) {
        Preparation preparation = new Preparation(context, logger, params, currentActiveApplicationSet, tenantPath);
        preparation.preprocess();
        try {
            preparation.buildModels(now);
            preparation.makeResult();
            if ( ! params.isDryRun()) {
                preparation.writeStateZK();
                preparation.writeRotZK();
                preparation.distribute();
                preparation.reloadDeployFileDistributor();
            }
            return preparation.result();
        } catch (OutOfCapacityException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw new InvalidApplicationException("Invalid application package", e);
        }
    }