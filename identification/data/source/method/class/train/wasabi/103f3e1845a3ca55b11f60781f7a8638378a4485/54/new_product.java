@GET
    @Path("/{applicationName}/experiments/{experimentLabel}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Return metadata for a single experiment",
            response = Experiment.class)
    @Timed
    public Response getExperiment(
            @PathParam("applicationName")
            @ApiParam(value = "Application Name")
            final Application.Name applicationName,

            @PathParam("experimentLabel")
            @ApiParam(value = "Experiment Label")
            final Experiment.Label experimentLabel,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            Experiment experiment = authorizedExperimentGetter
                    .getAuthorizedExperimentByName(authorizationHeader, applicationName, experimentLabel);

            return httpHeader.headers().entity(experiment).build();
        } catch (Exception exception) {
            LOGGER.error("getExperiment failed for applicationName={} & experimentLabel={} with error:",
                    applicationName,
                    experimentLabel,
                    exception);
            throw exception;
        }
    }