public ConfigChangeActions prepare(SessionContext context, DeployLogger logger, PrepareParams params,
                                       Optional<ApplicationSet> currentActiveApplicationSet,
                                       Optional<com.yahoo.component.Version> currentActiveVespaVersion, Path tenantPath,
                                       Instant now) {
        Preparation preparation = new Preparation(context, logger, params, currentActiveApplicationSet,
                                                  currentActiveVespaVersion, tenantPath);
        preparation.preprocess();
        try {
            AllocatedHosts allocatedHosts = preparation.buildModels(now);
            preparation.makeResult(allocatedHosts);
            if ( ! params.isDryRun()) {
                preparation.writeStateZK();
                preparation.writeRotZK();
                preparation.distribute();
                preparation.reloadDeployFileDistributor();
            }
            log.log(LogLevel.DEBUG, () -> "time used " + params.getTimeoutBudget().timesUsed() +
                    " : " + params.getApplicationId());
            return preparation.result();
        } catch (OutOfCapacityException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw new InvalidApplicationException("Invalid application package", e);
        }
    }