@GET
    @Path("/experiments/{experimentID}/assignments/counts")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Return a summary of assignments delivered for an experiment",
            response = AssignmentCounts.class)
    @Timed
    public Response getAssignmentCounts(
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
        authorizedExperimentGetter.getAuthorizedExperimentById(authorizationHeader, experimentID);

        AssignmentCounts assignmentCounts = analytics.getAssignmentCounts(experimentID, context);

        return httpHeader.headers().entity(assignmentCounts).build();
    }