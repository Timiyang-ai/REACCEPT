@POST
    @Path("{experimentID}/events")
    @Produces(TEXT_PLAIN)
    @ApiOperation(value = "Export all event records for an experiment",
            notes = "Download all event records for a given experiment in a tab-delimited text format.",
            response = StreamingOutput.class)
    @Timed
    public Response exportActions(
            @PathParam("experimentID")
            @ApiParam(value = "Experiment ID")
            final Experiment.ID experimentID,

            final Parameters parameters,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            Username userName = authorization.getUser(authorizationHeader);
            Experiment experiment = experiments.getExperiment(experimentID);

            // Throw an exception if the current experiment is not valid
            if (experiment == null) {
                throw new ExperimentNotFoundException(experimentID);
            }

            authorization.checkUserPermissions(userName, experiment.getApplicationName(), READ);

            StreamingOutput stream = export.getEventStream(experimentID, parameters);

            return httpHeader.headers().
                    header("Content-Disposition", "attachment; filename=\"events.csv\"").
                    entity(stream).type(TEXT_PLAIN).build();
        } catch (Exception exception) {
            LOGGER.error("exportActions failed for experimentID={} with error:", experimentID, exception);
            throw exception;
        }
    }