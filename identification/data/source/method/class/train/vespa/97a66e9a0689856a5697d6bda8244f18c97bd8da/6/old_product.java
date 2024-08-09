public ConfigChangeActions prepare(SessionContext context, DeployLogger logger, PrepareParams params,
                                       Optional<ApplicationSet> currentActiveApplicationSet, Path tenantPath, 
                                       Instant now) {
        Preparation preparation = new Preparation(context, logger, params, currentActiveApplicationSet, tenantPath);
        preparation.preprocess();
        try {
            AllocatedHosts allocatedHosts = preparation.buildModels(now);
            preparation.makeResult(allocatedHosts);
            if ( ! params.isDryRun()) {
                preparation.writeStateZK();
                preparation.writeRotZK();
                preparation.writeTlsZK();
                var globalServiceId = context.getApplicationPackage().getDeployment()
                                             .map(DeploymentSpec::fromXml)
                                             .flatMap(DeploymentSpec::globalServiceId);
                preparation.writeContainerEndpointsZK(globalServiceId);
                preparation.distribute();
            }
            log.log(LogLevel.DEBUG, () -> "time used " + params.getTimeoutBudget().timesUsed() +
                    " : " + params.getApplicationId());
            return preparation.result();
        }
        catch (IllegalArgumentException e) {
            throw new InvalidApplicationException("Invalid application package", e);
        }
    }