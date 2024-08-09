@GET
    @Path("applications/{applicationName}/experiments/{experimentLabel}/users/{userID}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Return bucket assignment for a user",
            notes = "Generate the assignment first if the user has no assignment " +
                    "for this experiment.  Return null if the user is not in the experiment.")
    @Timed
    public Response getAssignment(
                                    @PathParam("applicationName")
                                    @ApiParam(value = "Application Name")
                                    final Application.Name applicationName,

                                    @PathParam("experimentLabel")
                                    @ApiParam(value = "Experiment Label")
                                    final Experiment.Label experimentLabel,

                                    @PathParam("userID")
                                    @ApiParam(value = "User(customer) ID")
                                    final User.ID userID,

                                    @QueryParam("context")
                                    @DefaultValue("PROD")
                                    @ApiParam(value = "context for the experiment, e.g. PROD, QA")
                                    final Context context,

                                    @QueryParam("createAssignment")
                                    @DefaultValue("true")
                                    @ApiParam(value = "whether an assignment should be generated if one doesn't exist",
                                            defaultValue = "true")
                                    final Boolean createAssignment,

                                    @QueryParam("ignoreSamplingPercent")
                                    @DefaultValue("false")
                                    @ApiParam(value = "whether the sampling percent for the experiment should be ignored, " +
                                            "forcing the user into the experiment (if eligible)",
                                            defaultValue = "false")
                                    final Boolean ignoreSamplingPercent,

                                    @javax.ws.rs.core.Context
                                    final HttpHeaders headers) {
        Assignment assignment = getAssignment(userID, applicationName, experimentLabel, context, createAssignment,
                ignoreSamplingPercent, null, headers);

        return httpHeader.headers().entity(toMap(assignment)).build();
    }