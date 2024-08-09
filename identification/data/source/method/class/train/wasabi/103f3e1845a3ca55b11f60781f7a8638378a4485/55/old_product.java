@GET
    @Path("{applicationName}/pages")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Get the set of pages associated with an application.")
    @Timed
    public Response getPagesForApplication(@PathParam("applicationName")
                                           @ApiParam(value = "Application Name")
                                           final Application.Name applicationName,

                                           @HeaderParam(AUTHORIZATION)
                                           @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                           final String authorizationHeader) {
        authorization.checkUserPermissions(authorization.getUser(authorizationHeader), applicationName, READ);

        ImmutableMap<String, List<Page>> applicationPages = ImmutableMap.<String, List<Page>>builder()
                .put("pages", pages.getPageList(applicationName)).build();

        return httpHeader.headers().entity(applicationPages).build();
    }