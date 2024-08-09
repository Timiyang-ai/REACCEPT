@GET
    @Path("/users/{userID}/roles")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Get access roles for a user across applications")
    @Timed
    public Response getUserRole(@PathParam("userID")
                                @ApiParam(value = "User ID")
                                final Username userID,

                                @HeaderParam(AUTHORIZATION)
                                @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                final String authorizationHeader) {
        Username userName = authorization.getUser(authorizationHeader);
        UserRoleList userRoles = authorization.getUserRoleList(userID);

        if (userName.equals(userID)) {
            return httpHeader.headers().entity(userRoles).build();
        }

        UserRoleList authRoles = new UserRoleList();

        for (UserRole userRole : userRoles.getRoleList()) {
            try {
                authorization.checkUserPermissions(userName, userRole.getApplicationName(), ADMIN);
                authRoles.addRole(userRole);
            } catch (AuthenticationException ignored) {
                // FIXME: ?are we right in intentionally swallowing this exception?
                LOGGER.trace("AuthenticationException in getUserRole", ignored);
            }
        }

        return httpHeader.headers().entity(authRoles).build();
    }