@GET
    @Path("{applicationName}/pages/{pageName}/experiments")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Get the experiments associated to a page",
            notes = "The experiments returned belong to a single application")
    @Timed
    public Response getExperiments(@PathParam("applicationName")
                                   @ApiParam(value = "Application Name")
                                   final Application.Name applicationName,

                                   @PathParam("pageName")
                                   final Page.Name pageName,

                                   @HeaderParam(AUTHORIZATION)
                                   @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                   final String authorizationHeader) {
        authorization.checkUserPermissions(authorization.getUser(authorizationHeader), applicationName, READ);

        ImmutableMap<String, List<PageExperiment>> pageExperiments =
                ImmutableMap.<String, List<PageExperiment>>builder().put("experiments",
                        pages.getExperiments(applicationName, pageName)).build();

        return httpHeader.headers().entity(pageExperiments).build();
    }