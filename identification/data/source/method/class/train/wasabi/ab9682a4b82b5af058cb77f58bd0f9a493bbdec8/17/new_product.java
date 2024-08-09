@POST
    @Path("{experimentID}/exclusions")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Create mutual exclusion rules for an experiment",
            notes = "Can only create mutual exclusion rules for experiments in DRAFT, " +
                    "RUNNING, and PAUSED states within+" +
                    "the same application")
//          response = ??, //todo: update with proper object in @ApiOperation
    @Timed
    public Response createExclusions(
            @PathParam("experimentID")
            @ApiParam(value = "Experiment ID")
            final Experiment.ID experimentID,

            final ExperimentIDList experimentIDList,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            Username userName = authorization.getUser(authorizationHeader);
            Experiment experiment = experiments.getExperiment(experimentID);

            // Throw an exception if the current experiment is not valid
            if (experiment == null) {
                throw new ExperimentNotFoundException(experimentID);
            }

            authorization.checkUserPermissions(userName, experiment.getApplicationName(), CREATE);

            //this is the user that triggered the event and will be used for logging
            UserInfo user = authorization.getUserInfo(userName);
            List<Map> exclusions = mutex.createExclusions(experimentID, experimentIDList, user);

            return httpHeader.headers(CREATED)
                    .entity(ImmutableMap.<String, Object>builder().put("exclusions", exclusions).build()).build();
        } catch (Exception exception) {
            LOGGER.error("createExclusions failed for experimentID={}, experimentIDList={} with error:",
                    experimentID, experimentIDList, exception);
            throw exception;
        }
    }