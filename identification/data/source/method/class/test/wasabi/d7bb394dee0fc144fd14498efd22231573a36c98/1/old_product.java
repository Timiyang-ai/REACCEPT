@POST
    @Path("flushMessages")
    @Produces(APPLICATION_JSON)
    public Response flushMessages(
            @HeaderParam(AUTHORIZATION) @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true) final String authorizationHeader) {
        Username userName = authorization.getUser(authorizationHeader);
        authorization.checkSuperAdmin(userName);
        assignments.flushMessages();
        return httpHeader.headers().build();
    }