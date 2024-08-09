@GET
    @Path("{applicationName}/pages")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Get the set of pages associated with an application.")
    @Timed
    public Response getPagesForApplication(
            @PathParam("applicationName")
            @ApiParam(value = "Application Name")
            final Application.Name applicationName,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            authorization.checkUserPermissions(authorization.getUser(authorizationHeader), applicationName, READ);

            ImmutableMap<String, List<Page>> applicationPages = ImmutableMap.<String, List<Page>>builder()
                    .put("pages", pages.getPageList(applicationName)).build();

            return httpHeader.headers().entity(applicationPages).build();
        } catch (Exception exception) {
            LOGGER.error("getPagesForApplication failed for applicationName={} with error:", applicationName,
                    exception);
            throw exception;
        }
    }