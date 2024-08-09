@GET
    @Path("/applications/{applicationName}/experiments/{experimentLabel}/assignments/counts")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Return a summary of assignments delivered for an experiment",
            response = AssignmentCounts.class)
    @Timed
    public Response getAssignmentCountsByApp(
            @PathParam("applicationName")
            @ApiParam("Application Name")
            final Application.Name applicationName,

            @PathParam("experimentLabel")
            @ApiParam(value = "Experiment Label")
            final Experiment.Label experimentLabel,

            @QueryParam("context")
            @DefaultValue("PROD")
            @ApiParam(value = "context for the experiment, eg \"QA\", \"PROD\"")
            final Context context,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            Experiment experiment = authorizedExperimentGetter
                    .getAuthorizedExperimentByName(authorizationHeader, applicationName, experimentLabel);
            AssignmentCounts assignmentCounts = analytics.getAssignmentCounts(experiment.getID(), context);

            return httpHeader.headers().entity(assignmentCounts).build();
        } catch (Exception exception) {
            LOGGER.error("getAssignmentCountsByApp failed for applicationName={}, "
                            + "experimentLabel={}, context={} with error:",
                    applicationName, experimentLabel, context,
                    exception);
            throw exception;
        }
    }