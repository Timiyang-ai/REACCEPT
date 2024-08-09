@GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Get all feedback")
    @Timed
    public Response getAllUserFeedback(@HeaderParam(AUTHORIZATION)
                                       @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                       final String authorizationHeader) {
        authorization.checkSuperAdmin(authorization.getUser(authorizationHeader));

        ImmutableMap<String, List<UserFeedback>> userFeedback =
                new ImmutableMap.Builder<String, List<UserFeedback>>()
                        .put("feedback", feedback.getAllUserFeedback()).build();

        return httpHeader.headers().entity(userFeedback).build();
    }