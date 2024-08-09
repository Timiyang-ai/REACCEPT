@POST
    @Path("/experiments/{experimentID}/counts/dailies")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Return a daily summary of the data collected for the experiment",
            notes = "Return unique and non-unique counts at the experiment, bucket, and action levels " +
                    "for both actions and impressions. For each day, includes counts for that day and " +
                    "cumulative counts, calculated from the beginning of the experiment.",
            response = ExperimentCumulativeCounts.class)
    @Timed
    public Response getExperimentCountsDailiesParameters(
            @PathParam("experimentID")
            @ApiParam(value = "Experiment ID")
            final Experiment.ID experimentID,

            @ApiParam(required = true, defaultValue = DEFAULT_EMPTY)
            final Parameters parameters,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            authorizedExperimentGetter.getAuthorizedExperimentById(authorizationHeader, experimentID);
            parameters.parse();

            ExperimentCumulativeCounts experimentCumulativeCounts = analytics
                    .getExperimentRollupDailies(experimentID, parameters);

            return httpHeader.headers().entity(experimentCumulativeCounts).build();
        } catch (Exception exception) {
            LOGGER.error("getExperimentCountsDailiesParameters failed for experimentID={}, parameters={} with error:",
                    experimentID, parameters, exception);
            throw exception;
        }
    }