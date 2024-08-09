@GET
    @Path("/experiments/{experimentID}/counts/dailies")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "A wrapper for POST /counts/dailies API with default parameters",
            response = ExperimentCumulativeCounts.class)
    @Timed
    public Response getExperimentCountsDailies(
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

            return getExperimentCountsDailiesParameters(experimentID, parameters, authorizationHeader);
        } catch (Exception exception) {
            LOGGER.error("getExperimentCountsDailies failed for experimentID={}, context={} with error:",
                    experimentID, context, exception);
            throw exception;
        }
    }