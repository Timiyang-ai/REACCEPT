@POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Submit feedback")
    @Timed
    public Response postFeedback(@ApiParam(name = "userFeedback", value = "Please see model example", required = true)
                                 final UserFeedback userFeedback,

                                 @HeaderParam(AUTHORIZATION)
                                 @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                 final String authorizationHeader) {
        userFeedback.setUsername(authorization.getUser(authorizationHeader));
        feedback.createUserFeedback(userFeedback);

        return httpHeader.headers(CREATED).build();
    }