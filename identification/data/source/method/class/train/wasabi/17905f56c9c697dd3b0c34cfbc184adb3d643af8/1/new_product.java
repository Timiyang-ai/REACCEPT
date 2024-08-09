@GET
    @Path("metadataCacheDetails")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Get assignments metadata cache details...")
    @Timed
    public Response getMetadataCacheDetails(
            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            UserInfo.Username userName = authorization.getUser(authorizationHeader);
            authorization.checkSuperAdmin(userName);

            return httpHeader.headers().entity(assignments.metadataCacheDetails()).build();
        } catch (Exception exception) {
            LOGGER.error("getMetadataCacheDetails failed with error:", exception);
            throw exception;
        }
    }