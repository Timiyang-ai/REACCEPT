@GET
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Returns all applications")
    @Timed
    public Response getApplications(@HeaderParam(AUTHORIZATION)
                                    @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                    final String authorizationHeader) {
        if (authorization.getUser(authorizationHeader) == null) {
            throw new AuthenticationException("User is not authenticated");
        }

        List<Application.Name> applications = experiments.getApplications();

        return httpHeader.headers().entity(applications).build();
    }