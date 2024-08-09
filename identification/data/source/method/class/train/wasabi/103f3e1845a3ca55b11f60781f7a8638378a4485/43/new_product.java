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
            //Check whether user is authenticated
            UserInfo.Username username=authorization.getUser(authorizationHeader);

            if (this.rateLimitEnabled) {
                SimpleRateLimiter rateLimiter = getRateLimiter(username.toString());
                boolean allowRequest = rateLimiter.tryAcquire();
                if (!allowRequest) {
                    return httpHeader.headers(429).build();
                }
            }

            //check whether user is authorized
            UserRoleList userRoleList = authorization.getUserRoleList(username);
            boolean isAdmin = userRoleList.getRoleList().stream().anyMatch((UserRole ur) ->
                    (ur.getRole().equals(Role.SUPERADMIN) || ur.getRole().equals(Role.ADMIN)));
            if (!isAdmin) {
                throw new AuthenticationException("Error, user " + username + " is not authorized");
            }

            return httpHeader.headers().entity(authentication.getUserExists(userEmail)).build();
        } catch (Exception exception) {
            LOGGER.error("getUserExists failed for userEmail={} with error:", userEmail, exception);
            throw exception;
        }
    }