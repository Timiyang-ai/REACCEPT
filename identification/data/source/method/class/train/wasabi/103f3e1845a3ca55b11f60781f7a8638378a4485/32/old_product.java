@POST
    @Path("applications/{applicationName}/pages/{pageName}/users/{userID}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Return bucket assignments for a user for all the experiments associated with a page",
            notes = "The mutual exclusion and segmentation rules apply")
    @Timed
    public Response postBatchAssignmentForPage(
                                            @PathParam("applicationName")
                                            @ApiParam(value = "Application Name")
                                            final Application.Name applicationName,

                                            @PathParam("pageName")
                                            @ApiParam("Page Name")
                                            Page.Name pageName,

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

                                            @ApiParam(value = "Segmentation Profile")
                                            final SegmentationProfile segmentationProfile,

                                            @javax.ws.rs.core.Context final HttpHeaders headers) {
    	
    	if ( LOGGER.isDebugEnabled()) {
    		LOGGER.debug("postBatchAssignmentForPage applicationName={}, pageName={}, userID={}, context={}, createAssignment={}" +
                                    ", ignoreSamplingPercent={}, headers={}, segmentationProfile={}", applicationName, 
                                    pageName, userID, context, createAssignment, ignoreSamplingPercent, headers, segmentationProfile);
    	}

        List<Map> assignmentsFromPage = assignments.doPageAssignments(applicationName, pageName, userID, context,
                createAssignment, ignoreSamplingPercent, headers, segmentationProfile);

        return httpHeader.headers()
                .entity(ImmutableMap.<String, Object>builder().put("assignments", assignmentsFromPage).build()).build();
    }