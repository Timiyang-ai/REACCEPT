@GET
    @Path("/verifyToken")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Verify user's authorization")
    @Timed
    public Response verifyToken(@HeaderParam(AUTHORIZATION)
                                @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                final String tokenHeader) {
        return httpHeader.headers().entity(authentication.verifyToken(tokenHeader)).build();
    }