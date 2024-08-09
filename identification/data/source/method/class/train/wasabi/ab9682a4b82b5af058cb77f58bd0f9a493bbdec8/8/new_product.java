@GET
    @Path("{experimentID}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Return details of a single experiment",
            response = Experiment.class)
    @Timed
    public Response getExperiment(
            @PathParam("experimentID")
            @ApiParam(value = "Experiment ID")
            final Experiment.ID experimentID,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            Experiment experiment = experiments.getExperiment(experimentID);

            if (experiment == null) {
                throw new ExperimentNotFoundException(experimentID);
            }

            if (authorizationHeader != null) {
                Username userName = authorization.getUser(authorizationHeader);

                authorization.checkUserPermissions(userName, experiment.getApplicationName(), READ);
            }

            return httpHeader.headers().entity(experiment).type(APPLICATION_JSON_TYPE).build();
        } catch (Exception exception) {
            LOGGER.error("getExperiment failed for experimentID={} with error:", experimentID, exception);
            throw exception;
        }
    }