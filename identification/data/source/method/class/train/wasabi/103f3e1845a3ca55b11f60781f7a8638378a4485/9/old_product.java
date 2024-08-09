@GET
    @Path("/users/{userID}/applications/{applicationName}/permissions")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Get permissions of one user within a single application")
    @Timed
    public Response getUserAppPermissions(@PathParam("userID")
                                          @ApiParam(value = "User ID")
                                          final Username userID,

                                          @PathParam("applicationName")
                                          @ApiParam(value = "Application Name")
                                          final Application.Name applicationName,

                                          @HeaderParam(AUTHORIZATION)
                                          @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                          final String authorizationHeader) {
        Username userName = authorization.getUser(authorizationHeader);

        if (!userName.equals(userID)) {
            authorization.checkUserPermissions(userName, applicationName, ADMIN);
        }

        UserPermissions userPermissions = authorization.getUserPermissions(userID, applicationName);

        return httpHeader.headers().entity(userPermissions).build();
    }