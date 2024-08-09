@GET
    @Path("/users/{userID}/permissions")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Get permissions for a user across applications")
    @Timed
    public Response getUserPermissions(
            @PathParam("userID")
            @ApiParam(value = "User ID")
            final Username userID,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            Username userName = authorization.getUser(authorizationHeader);
            UserPermissionsList userPermissionsList = authorization.getUserPermissionsList(userID);

            if (userName.equals(userID)) {
                return httpHeader.headers().entity(userPermissionsList).build();
            }

            UserPermissionsList authPermissionsList = new UserPermissionsList();

            for (UserPermissions userPermissions : userPermissionsList.getPermissionsList()) {
                try {
                    authorization.checkUserPermissions(userName, userPermissions.getApplicationName(), ADMIN);
                    authPermissionsList.addPermissions(userPermissions);
                } catch (AuthenticationException ignored) {
                    // FIXME: ?are we right in intentionally swallowing this excpetion?
                    LOGGER.trace("AuthenticationException in getUserPermissions", ignored);
                }
            }

            return httpHeader.headers().entity(authPermissionsList).build();
        } catch (Exception exception) {
            LOGGER.error("getUserPermissions failed for userID={} with error:", userID, exception);
            throw exception;
        }
    }