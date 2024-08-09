@GET
    @Path("/experiments/{experimentID}/statistics")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "A wrapper for POST /statistics API with default parameters",
            response = ExperimentStatistics.class)
    @Timed
    public Response getExperimentStatistics(
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
        // note: auth not required because the called method is authorized add context value to parameters
        Parameters parameters = createParameters(context);

        return getExperimentStatisticsParameters(experimentID, parameters, authorizationHeader);
    }