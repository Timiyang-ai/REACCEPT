@POST
    @Path("/experiments/{experimentID}/statistics")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Return a summary of the data collected for the experiment with derived statistics",
            notes = "Return unique and non-unique counts at the experiment, bucket, and action levels " +
                    "for both actions and impressions. Also returns a number of statistics calculated " +
                    "from the unique counts.",
            response = ExperimentStatistics.class)
    @Timed
    public Response getExperimentStatisticsParameters(
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

        ExperimentStatistics experimentStatistics = analytics.getExperimentStatistics(experimentID, parameters);

        return httpHeader.headers().entity(experimentStatistics).build();
    }