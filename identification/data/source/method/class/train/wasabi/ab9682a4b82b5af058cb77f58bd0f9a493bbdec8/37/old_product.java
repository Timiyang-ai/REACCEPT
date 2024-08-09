@GET
    @Path("/applications/{applicationName}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Get roles for all users within an application")
    @Timed
    public Response getApplicationUsersByRole(
            @PathParam("applicationName")
            @ApiParam(value = "Application Name")
            final Application.Name applicationName,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            // As long as you are an authenticated user, anyone should be able to see list of applications and admins
            Username userName = authorization.getUser(authorizationHeader);

            if (userName == null) {
                throw new AuthenticationException("User is not authenticated");
            }

            return httpHeader.headers().entity(authorization.getApplicationUsers(applicationName)).build();
        } catch (Exception exception) {
            LOGGER.error("getApplicationUsersByRole failed for applicationName={} with error:",
                    applicationName, exception);
            throw exception;
        }
    }