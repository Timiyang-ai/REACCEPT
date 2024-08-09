@GET
    @Path("/experiments/{experimentID}/counts")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "A wrapper for POST /counts API with default parameters",
            response = ExperimentCounts.class)
    @Timed
    public Response getExperimentCounts(
            @PathParam("experimentID")
            @ApiParam(value = "Experiment ID")
            final Experiment.ID experimentID,

            @QueryParam("context")
            @DefaultValue("PROD")
            @ApiParam(value = "context for the experiment, eg \"QA\", \"PROD\"")
            final Context context,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        // note: auth not required because the called method is authorized add context value to parameters
        Parameters parameters = createParameters(context);

        return getExperimentCountsParameters(experimentID, parameters, authorizationHeader);
    }