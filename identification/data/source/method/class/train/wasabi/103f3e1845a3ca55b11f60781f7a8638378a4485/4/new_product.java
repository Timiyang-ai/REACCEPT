@GET
    @Path("/users/{userEmail}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Check if user exists using user's email")
    @Timed
    public Response getUserExists(
            @PathParam("userEmail")
            @ApiParam(value = "Email of the user")
            final String userEmail,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            authorization.getUser(authorizationHeader);
            return httpHeader.headers().entity(authentication.getUserExists(userEmail)).build();
        } catch (Exception exception) {
            LOGGER.error("getUserExists failed for userEmail={} with error:", userEmail, exception);
            throw exception;
        }
    }