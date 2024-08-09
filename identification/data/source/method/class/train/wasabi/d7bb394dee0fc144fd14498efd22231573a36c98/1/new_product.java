@POST
    @Path("flushMessages")
    @Produces(APPLICATION_JSON)
    public Response flushMessages(
            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            Username userName = authorization.getUser(authorizationHeader);
            authorization.checkSuperAdmin(userName);
            assignments.flushMessages();
            return httpHeader.headers(HttpStatus.SC_NO_CONTENT).build();
        } catch (Exception exception) {
            LOGGER.error("flushMessages failed with error:", exception);
            throw exception;
        }
    }