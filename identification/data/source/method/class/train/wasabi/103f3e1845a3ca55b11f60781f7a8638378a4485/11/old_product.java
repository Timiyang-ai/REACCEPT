@POST
    @Path("applications/{applicationName}/experiments/{experimentLabel}/users/{userID}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Return bucket assignment for a user",
            notes = "Generate the assignment first if the user has no assignment for this experiment. " +
                    "Forces the user to be in the experiment (if eligible based on profile).")
    @Timed
    public Response postAssignment(
            @PathParam("applicationName")
            @ApiParam(value = "Application Name")
            final Application.Name applicationName,

            @PathParam("experimentLabel")
            @ApiParam(value = "Experiment Label")
            final Experiment.Label experimentLabel,

            @PathParam("userID")
            @ApiParam(value = "User(customer) ID")
            final User.ID userID,

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

            @QueryParam("context")
            @DefaultValue("PROD")
            @ApiParam(value = "context for the experiment, e.g. PROD, QA")
            final Context context,

            @ApiParam(name = "segmentationProfile", value = "Segmentation Profile")
            final SegmentationProfile segmentationProfile,

            @javax.ws.rs.core.Context
            final HttpHeaders headers) {
        try {
            LOGGER.debug("postAssignment userID={}, applicationName={}, experimentLabel={}, context={},"
                            + " createAssignment={}, ignoreSamplingPercent={}, segmentationProfile={}, headers={}",
                    userID, applicationName, experimentLabel, context, createAssignment, ignoreSamplingPercent,
                    segmentationProfile, headers);

            Assignment assignment = getAssignment(userID, applicationName, experimentLabel, context, createAssignment,
                    ignoreSamplingPercent, segmentationProfile, headers);

            return httpHeader.headers().entity(toSingleAssignmentResponseMap(assignment)).build();
        } catch (Exception exception) {
            LOGGER.error("postAssignment failed for applicationName={}, experimentLabel={}, userID={}, context={},"
                            + " createAssignment={}, ignoreSamplingPercent={}," +
                            " segmentationProfile={}, headers={} with error:",
                    applicationName, experimentLabel, userID, context, createAssignment,
                    ignoreSamplingPercent, segmentationProfile, headers,
                    exception);
            throw exception;
        }
    }