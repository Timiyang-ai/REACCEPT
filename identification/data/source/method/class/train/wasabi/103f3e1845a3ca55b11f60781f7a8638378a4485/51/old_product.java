@POST
    @Path("/experiments/{experimentID}/statistics/dailies")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Return a daily summary of the data collected for the experiment with derived statistics",
            notes = "Return unique and non-unique counts at the experiment, bucket, and action levels " +
                    "for both actions and impressions. For each day, includes counts for that day and " +
                    "cumulative counts, calculated from the beginning of the experiment. Also returns " +
                    "a number of statistics calculated from the unique counts.",
            response = ExperimentCumulativeStatistics.class)
    @Timed
    public Response getExperimentStatisticsDailiesParameters(
            @PathParam("experimentID")
            @ApiParam(value = "Experiment ID")
            final Experiment.ID experimentID,

            @ApiParam(required = true, defaultValue = DEFAULT_EMPTY)
            final Parameters parameters,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        authorizedExperimentGetter.getAuthorizedExperimentById(authorizationHeader, experimentID);
        parameters.parse();

        ExperimentCumulativeStatistics experimentCumulativeStatistics =
                analytics.getExperimentStatisticsDailies(experimentID, parameters);

        return httpHeader.headers().entity(experimentCumulativeStatistics).build();
    }