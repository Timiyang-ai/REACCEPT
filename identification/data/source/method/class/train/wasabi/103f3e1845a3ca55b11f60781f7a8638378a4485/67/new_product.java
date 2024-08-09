@DELETE
    @Path("/applications/{applicationName}/users/{userID}/roles")
    @Produces(APPLICATION_JSON)
//    @RolesAllowed("ADMIN")
    @ApiOperation(value = "Delete a user's role within an application")
    @Timed
    public Response deleteUserRoles(
            @PathParam("applicationName")
            @ApiParam(value = "Application Name")
            final Application.Name applicationName,

            @PathParam("userID")
            @ApiParam(value = "User ID")
            final Username userID,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            Username userName = authorization.getUser(authorizationHeader);
            UserInfo admin = authorization.getUserInfo(userName);

            authorization.checkUserPermissions(userName, applicationName, ADMIN);
            authorization.deleteUserRole(userID, applicationName, admin);

            return httpHeader.headers(NO_CONTENT).build();
        } catch (Exception exception) {
            LOGGER.error("deleteUserRoles failed for applicationName={}, userID={} with error:",
                    applicationName, userID, exception);
            throw exception;
        }
    }