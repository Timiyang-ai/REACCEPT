@GET
    @Path("applications/{applicationName}/pages/{pageName}/users/{userID}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Return bucket assignments for a user for all the experiments associated with a page",
            notes = "If you want to pass segmentation information, use the POST-Call for this method")
    @Timed
    public Response getBatchAssignmentForPage(
            @PathParam("applicationName")
            @ApiParam(value = "Application Name")
            final Application.Name applicationName,

            @PathParam("pageName")
            @ApiParam(value = "Page Name")
            final Page.Name pageName,

            @PathParam("userID")
            @ApiParam(value = "User(customer) ID")
            final User.ID userID,

            @QueryParam("createAssignment")
            @DefaultValue("true")
            @ApiParam(value = "conditional to generate an assignment if one doesn't exist",
                    defaultValue = "true")
            final boolean createAssignment,

            @QueryParam("ignoreSamplingPercent")
            @DefaultValue("false")
            @ApiParam(value = "whether the sampling percent for the experiment should be ignored, " +
                    "forcing the user into the experiment (if eligible)",
                    defaultValue = "false")
            final boolean ignoreSamplingPercent,

            @QueryParam("context")
            @DefaultValue("PROD")
            @ApiParam(value = "context for the experiment, eg QA, PROD")
            final Context context,

            @javax.ws.rs.core.Context
                    HttpHeaders headers) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("getBatchAssignmentsForPage applicationName={}, pageName={}, userID={}, context={}, createAssignment={}" +
                                ", ignoreSamplingPercent={}, headers={}", applicationName, pageName, userID, context, createAssignment,
                        ignoreSamplingPercent, headers);
            }

            List<Assignment> assignmentsFromPage = assignments.doPageAssignments(applicationName, pageName, userID, context,
                    createAssignment, ignoreSamplingPercent, headers, null);

            return httpHeader.headers()
                    .entity(ImmutableMap.<String, Object>builder().put("assignments", toMap(assignmentsFromPage)).build()).build();
        } catch (Exception e) {
            LOGGER.error("Exception happened while batch-assignment [GET]...", e);
            throw e;
        }
    }