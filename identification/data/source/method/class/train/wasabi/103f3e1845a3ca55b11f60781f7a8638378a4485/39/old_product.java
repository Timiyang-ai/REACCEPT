@GET
    @Path("/logout")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Log a user out")
    @Timed
    public Response logUserOut(@HeaderParam(AUTHORIZATION)
                               @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                               final String tokenHeader) {
        authentication.logOut(tokenHeader);
        return httpHeader.headers(NO_CONTENT).build();
    }