@GET
    @Path("/users/{username}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Get single user feedback")
    @Timed
    public Response getUserFeedback(
            @PathParam("username")
            @ApiParam(value = "User name")
            final UserInfo.Username username,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            authorization.checkSuperAdmin(authorization.getUser(authorizationHeader));

            ImmutableMap<String, List<UserFeedback>> userFeedback =
                    new ImmutableMap.Builder<String, List<UserFeedback>>()
                    .put("feedbackList", feedback.getUserFeedback(username)).build();

            return httpHeader.headers().entity(userFeedback).build();
        } catch (Exception exception) {
            LOGGER.error("getUserFeedback failed for username={} with error:", username, exception);
            throw exception;
        }
    }