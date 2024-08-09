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