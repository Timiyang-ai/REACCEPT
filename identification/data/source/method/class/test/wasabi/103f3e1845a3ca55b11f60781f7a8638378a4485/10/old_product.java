@GET
    @Path("/users/{userEmail}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Check if user exists using user's email")
    @Timed
    public Response getUserExists(@PathParam("userEmail")
                                  @ApiParam(value = "Email of the user")
                                  final String userEmail) {
        return httpHeader.headers().entity(authentication.getUserExists(userEmail)).build();
    }