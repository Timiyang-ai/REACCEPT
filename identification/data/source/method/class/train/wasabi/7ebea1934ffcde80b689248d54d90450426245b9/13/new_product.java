@GET
    @Path("{experimentID}/exclusions")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Get list of mutually exclusive experiments",
            notes = "Shows list of all experiments, in all states, that are " +
                    "mutually exclusive with input experiment.")
    //            response = ??, //todo: update with proper object in @ApiOperation
    @Timed
    public Response getExclusions(
            @PathParam("experimentID")
            @ApiParam(value = "Experiment ID")
            final Experiment.ID experimentID,

            @QueryParam("showAll")
            @DefaultValue("true")
            final Boolean showAll,

            @QueryParam("exclusive")
            @DefaultValue("true")
            final Boolean exclusive,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            //TODO: Duplicate code. Move to separate method
            if (authorizationHeader != null) {
                Username userName = authorization.getUser(authorizationHeader);
                Experiment experiment = experiments.getExperiment(experimentID);

                if (experiment == null) {
                    throw new ExperimentNotFoundException(experimentID);
                }

                authorization.checkUserPermissions(userName, experiment.getApplicationName(), READ);
            }

            ExperimentList experimentList =
                    exclusive ? mutex.getExclusions(experimentID) : mutex.getNotExclusions(experimentID);
            ExperimentList returnedExperiments = experimentList;

            if (!showAll) {
                ExperimentList allExperiments = new ExperimentList();

                for (Experiment experiment : experimentList.getExperiments()) {
                    if (!experiment.getState().equals(TERMINATED) && !experiment.getState().equals(DELETED)) {
                        allExperiments.addExperiment(experiment);
                    }
                }

                returnedExperiments = allExperiments;
            }

            return httpHeader.headers().entity(returnedExperiments).build();
        } catch (Exception exception) {
            LOGGER.error("getExclusions failed for experimentID={}, showAll={}, exclusive={} with error:",
                    experimentID, showAll, exclusive,
                    exception);
            throw exception;
        }
    }