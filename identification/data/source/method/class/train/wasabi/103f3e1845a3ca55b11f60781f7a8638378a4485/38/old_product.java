@POST
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Log a user in")
    @Timed
    public Response logUserIn(@HeaderParam(AUTHORIZATION)
                              @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                              final String authorizationHeader,

                              @FormParam("grant_type")
                              @DefaultValue("client_credentials")
                              @ApiParam(value = "please enter client_credentials in this field")
                              final String grantType) {
        //FIXME: This should be taken out
        if (!"client_credentials".equals(grantType)) {
            throw new AuthenticationException("error, grant_type was not provided");
        }

        //pass the headers along to try and log the user in
        return httpHeader.headers().entity(authentication.logIn(authorizationHeader)).build();
    }