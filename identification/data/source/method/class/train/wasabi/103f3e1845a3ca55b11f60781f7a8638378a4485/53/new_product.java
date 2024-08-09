@GET
    @Path("/experiments/{experimentID}/statistics/dailies")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "A wrapper for POST /statistics/dailies API with default parameters",
            response = ExperimentCumulativeStatistics.class)
    @Timed
    public Response getExperimentStatisticsDailies(
            @PathParam("experimentID")
            @ApiParam(value = "Experiment ID")
            final Experiment.ID experimentID,

            @QueryParam("context")
            @DefaultValue("PROD")
            @ApiParam(value = "context for the experiment, eg QA, PROD")
            final Context context,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            // note: auth not required because the called method is authorized add context value to parameters
            Parameters parameters = createParameters(context);

            return getExperimentStatisticsDailiesParameters(experimentID, parameters, authorizationHeader);
        } catch (Exception exception) {
            LOGGER.error("getExperimentStatisticsDailies failed for experimentID={}, context={} with error:",
                    experimentID, context, exception);
            throw exception;
        }
    }